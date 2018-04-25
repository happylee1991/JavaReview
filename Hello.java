package com.lee.demo;

import com.google.gson.annotations.SerializedName;
import net.librec.common.LibrecException;
import net.librec.conf.Configuration;
import net.librec.data.model.TextDataModel;
import net.librec.eval.RecommenderEvaluator;
import net.librec.eval.rating.RMSEEvaluator;
import net.librec.filter.GenericRecommendedFilter;
import net.librec.recommender.Recommender;
import net.librec.recommender.RecommenderContext;
import net.librec.recommender.cf.ItemKNNRecommender;
import net.librec.recommender.item.RecommendedItem;
import net.librec.similarity.PCCSimilarity;
import net.librec.similarity.RecommenderSimilarity;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Hello {

    public static void main(String[] args) throws Exception{

        json04();


    }

    private static void json04() throws IOException {
        File file = new File(Hello.class.getResource("wxer.json").getFile());
        String jsonStr = FileUtils.readFileToString(file);
        System.out.println(jsonStr);
        JSONObject jsonObject = new JSONObject(jsonStr);
        System.out.println(jsonObject.getString("name"));
        System.out.println(jsonObject.getString("desc"));
        List<Object> list = jsonObject.getJSONArray("爱好  ").toList();
        for (Object aihao : list) {
            System.out.println(aihao);
        }

    }

    private static void json03() {
        Diaos diaos = new Diaos();
        diaos.setName("赵闪闪");
        diaos.setAge("25");
        diaos.setAihao(new String[]{"泡面", "小电影", "D8"});
        diaos.setDesc("绝代屌丝");


        JSONObject jsonObject = new JSONObject(diaos);
        System.out.println(jsonObject);
    }

    private static void json02() {
        Map<String, Object> jsonObject = new HashMap<String, Object>();
        jsonObject.put("name", "王小二");
        jsonObject.put("desc", "王小二是个好学生");
        jsonObject.put("爱好", new String[]{"健身", "读书"});
        JSONObject json = new JSONObject(jsonObject);
        System.out.println(json.toString());
    }

    private static void json01() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "王小二");
        jsonObject.put("desc", "王小二是个好学生");
        jsonObject.put("爱好", new String[]{"健身", "读书"});
        String s = jsonObject.toString();
        System.out.println(s);
    }

    private static void test01() throws LibrecException {
        // build data model
        Configuration conf = new Configuration();
        conf.set("dfs.data.dir", "C:\\Users\\Dragon\\Desktop\\librec-2.0.0\\data");
        TextDataModel dataModel = new TextDataModel(conf);
        dataModel.buildDataModel();

        // build recommender context
        RecommenderContext context = new RecommenderContext(conf, dataModel);

        // build similarity
        conf.set("rec.recommender.similarity.key" ,"item");
        RecommenderSimilarity similarity = new PCCSimilarity();
        similarity.buildSimilarityMatrix(dataModel);
        context.setSimilarity(similarity);

        // build recommender
        conf.set("rec.neighbors.knn.number", "50");
        Recommender recommender = new ItemKNNRecommender();
        recommender.setContext(context);

        // run recommender algorithm
        recommender.recommend(context);

        // evaluate the recommended result
        RecommenderEvaluator evaluator = new RMSEEvaluator();
        System.out.println("RMSE:" + recommender.evaluate(evaluator));

        // set id list of filter
        List<String> userIdList = new ArrayList<String>();
        List<String> itemIdList = new ArrayList<String>();
        userIdList.add("1");
        itemIdList.add("70");

        // filter the recommended result
        List<RecommendedItem> recommendedItemList = recommender.getRecommendedList();
        GenericRecommendedFilter filter = new GenericRecommendedFilter();
        filter.setUserIdList(userIdList);
        filter.setItemIdList(itemIdList);
        recommendedItemList = filter.filter(recommendedItemList);

        // print filter result
        for (RecommendedItem recommendedItem : recommendedItemList) {
            System.out.println(
                    "user:" + recommendedItem.getUserId() + " " +
                            "item:" + recommendedItem.getItemId() + " " +
                            "value:" + recommendedItem.getValue()
            );
        }
    }
}

