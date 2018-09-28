package com.example.ai.swuplant.base;

import android.app.Application;
import android.content.res.TypedArray;

import com.baidu.mapapi.SDKInitializer;
import com.example.ai.swuplant.R;
import com.example.ai.swuplant.entity.PlantModel;
import com.example.ai.swuplant.entity.PointInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseApplication extends Application {

    public static List<String> plantCnNameList = new ArrayList<>();
    public static List<String> plantEnNameList = new ArrayList<>();
    public static List<String> plantPropertyList = new ArrayList<>();
    public static List<String> plantDescriptionList = new ArrayList<>();
    public static List<String> plantDistributionList = new ArrayList<>();
    public static List<Integer> plantImageIdList = new ArrayList<>();
    public static List<PlantModel> plantModelList = new ArrayList<>();

    public static List<PointInfo> pointInfoList = new ArrayList<>();
    public static List<List<String>> pointList = new ArrayList<>();

    public static List<String> bambooList = new ArrayList<>();
    public static List<String> fernList = new ArrayList<>();
    public static List<String> gymnospermList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();

        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());

        init();
    }

    private void init(){
        plantCnNameList = Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.plantCN));
        plantEnNameList = Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.plantEn));
        plantPropertyList = Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.plantProperty));
        plantDescriptionList = Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.plantDescription));
        plantDistributionList = Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.plantDistribution));

        TypedArray ar = getApplicationContext().getResources().obtainTypedArray(
                R.array.plantImageId);
        int len = ar.length();
        for (int i = 0; i < len; i++){
            plantImageIdList.add(ar.getResourceId(i, 0));
        }
        ar.recycle();

        for (int i = 0; i < plantCnNameList.size(); i++) {
            PlantModel plantModel = new PlantModel(
                    plantImageIdList.get(i),
                    plantCnNameList.get(i),
                    plantEnNameList.get(i),
                    plantPropertyList.get(i),
                    plantDescriptionList.get(i),
                    plantDistributionList.get(i));
            plantModelList.add(plantModel);
        }


        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointOnePlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointTwoPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointThreePlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointFourPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointFivePlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointSixPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointSevenPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointEightPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointNinePlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointTenPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointElevenPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointTwelvePlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointThirteenPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointFourteenPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointFifteenPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointSixteenPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointSeventeenPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointEighteenPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointNineteenPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointTwentyPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointTwentyonePlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointTwentytwoPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointTwentythreePlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointTwentyfourPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointTwentyfivePlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointTwentysixPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointTwentysevenPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointTwentyeightPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointTwentyninePlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointThirtyPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointThirtyonePlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointThirtytwoPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointThirtythreePlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointThirtyfourPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointThirtysixPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointThirtysevenPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointThirtyeightPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointThirtyninePlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointFortyPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointFortyonePlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointFortytwoPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointFortythreePlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointFortyfourPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointFortyfivePlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointFortysixPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointFortysevenPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointFortyeightPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointFortyninePlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointFiftyPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointFiftyonePlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointFiftytwoPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointFiftythreePlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointFiftyfourPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointFiftyfivePlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointFiftysixPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointFiftysevenPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointFiftyeightPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointFiftyninePlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointSixtyPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointSixtyonePlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointSixtytwoPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointSixtythreePlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointSixtyfourPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointSixtyfivePlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointSixtysixPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointSixtysevenPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointSixtyeightPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointSixtyninePlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointSeventyPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointSeventyonePlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointSeventytwoPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointSeventythreePlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointSeventyfourPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointSeventyfivePlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointSeventysixPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointSeventysevenPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointSeventyeightPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointSeventyninePlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointEightyPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointEightyonePlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointEightytwoPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointEightythreePlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointEightyfourPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointEightyfivePlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointEightysixPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointEightysevenPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointEightyeightPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointEightyninePlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointNinetyPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointNinetyonePlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointNinetytwoPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointNinetythreePlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointNinetyfourPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointNinetyfivePlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointNinetysixPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointNinetysevenPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointNinetyeightPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointNinetyninePlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointOnehundredPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointOnehundredandonePlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointOnehundredandtwoPlant)));
        pointList.add(Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.pointOnehundredandthreePlant)));


        for (int i = 0; i < pointList.size(); i++) {
            List<String> stringList = pointList.get(i);

            double latitude = Double.valueOf(stringList.get(0));
            double longitude = Double.valueOf(stringList.get(1));
            List<String> nameList = new ArrayList<>();

            for (int j=2;j<stringList.size();j++){
                nameList.add(stringList.get(j));
            }
            int pointNumber = i;

            PointInfo pointInfo= new PointInfo(pointNumber,latitude,longitude,nameList);
            pointInfoList.add(pointInfo);
        }

        bambooList = Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.bambooList));
        fernList = Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.fernList));
        gymnospermList = Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.gymnospermList));


    }

}
