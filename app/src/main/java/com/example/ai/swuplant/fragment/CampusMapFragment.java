package com.example.ai.swuplant.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.ai.swuplant.R;
import com.example.ai.swuplant.activity.PlantDetailActivity;
import com.example.ai.swuplant.activity.PointInfoActivity;
import com.example.ai.swuplant.base.BaseFragment;
import com.example.ai.swuplant.customcomponent.ClearEditText;
import com.example.ai.swuplant.entity.PointInfo;
import com.example.ai.swuplant.utils.Constant;
import com.example.ai.swuplant.utils.IntentUtils;

import java.util.ArrayList;
import java.util.List;
import static com.example.ai.swuplant.activity.SplashActivity.plantModelList;
import static com.example.ai.swuplant.activity.SplashActivity.pointInfoList;

public class CampusMapFragment extends BaseFragment {

    private View view=null;
    private ClearEditText mSearchView;

    private MapView mMapView = null;
    private BaiduMap baiduMap = null;
    private LocationClient locationClient = null;

    private boolean isFirstLocation=true;

    private MyLocationListener myLocationListener;
    /**
     * 地图移位后回到原位，记录新的经纬度
     */
    private double mLatitude;
    private double mLongitude;
    //自定义定位图标
    private BitmapDescriptor mIconLocation;

    /**
     *模式变量
     */
    private MyLocationConfiguration.LocationMode mLocationMode;

    /**
     *覆盖物图标
     */
    private BitmapDescriptor mMarker;

    /**
     * infoWindow
     */
    private InfoWindow infoWindow;

    private static final String TAG = CampusMapFragment.class.getSimpleName();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSDKClient();
    }

    private void initSDKClient(){
        //初始化定位客户端
        locationClient=new LocationClient(getActivity().getApplicationContext());
        myLocationListener=new MyLocationListener();
        //注册监听器
        locationClient.registerLocationListener(myLocationListener);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_compus_map, container, false);
        initView(view);
        initEvent();

        checkPermission();
        checkGPS();

        addOverlays(pointInfoList);

        setOnMarkerListener();
        setOnMapClickListener();

        return view;
    }

    private void addOverlays(List<PointInfo> pointList){
        /**
         * 清除图层
         */
        baiduMap.clear();

        LatLng latLng=null;
        Marker marker=null;
        OverlayOptions options;

        for (PointInfo info:pointList) {

            //经纬度
            latLng=new LatLng(info.getLatitude(),info.getLongitude());
            //图标
            options=new MarkerOptions().position(latLng).icon(mMarker).zIndex(5);
            marker=(Marker)baiduMap.addOverlay(options);
            Bundle bundle=new Bundle();
            bundle.putSerializable(Constant.PLANT_NAME,info);

            marker.setExtraInfo(bundle);
        }
        //把地图移动到到最后一个覆盖物的位置
        MapStatusUpdate update=MapStatusUpdateFactory.newLatLng(latLng);
        baiduMap.setMapStatus(update);
    }

    /**
     * 点击地图的时候，隐藏具体信息、隐藏infoWindows
     */
    private void setOnMapClickListener() {
        baiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                /**
                 * 点击地图，隐藏infoWindows
                 */
                baiduMap.hideInfoWindow();

            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });
    }

    /**
     * 点击Marker显示marker具体信息和infoWindow
     */
    private void setOnMarkerListener() {
        baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                Bundle bundle=marker.getExtraInfo();
                PointInfo infoBean=(PointInfo) bundle.getSerializable(Constant.PLANT_NAME);

                /**
                 * 设置infoWindows
                 */

                TextView tv=new TextView(getActivity());
                tv.setBackground(getResources().getDrawable(R.drawable.popup));

                tv.setPadding(30,20,30,50);
                tv.setText(infoBean.getPointNumber()+"号点");

                LatLng latLng=marker.getPosition();


                infoWindow=new InfoWindow(tv,latLng,-47);


                baiduMap.showInfoWindow(infoWindow);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        IntentUtils.showActivity(getActivity(), PointInfoActivity.class,bundle);
                    }
                },3000);

                return true;
            }
        });
    }


    /**
     * 检查权限和申请权限
     */
    private void checkPermission() {
        List<String> permissionList=new ArrayList<String>();

        if (ContextCompat.checkSelfPermission(
                getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }

        if (ContextCompat.checkSelfPermission(
                getActivity(),
                Manifest.permission.READ_PHONE_STATE)!=
                PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(
                getActivity(),
                Manifest.permission.CAMERA)!=
                PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.CAMERA);

        }
        if (ContextCompat.checkSelfPermission(
                getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)!=
                PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (!permissionList.isEmpty()){
            String[] permissions=permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(getActivity(),permissions,1);
        }else{
            acquireLocation();
        }

    }

    /**
     * 检查GPS是否打开
     */
    private void checkGPS() {
        LocationManager locManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);

        if(!locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            // 未打开位置开关，可能导致定位失败或定位不准，提示用户或做相应处理
            Toast.makeText(getActivity(),
                    "未打开GPS开关，可能导致定位失败或定位不准",
                    Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 初始化一些定位的参数
     */
    private void initLocation(){

        mLocationMode= MyLocationConfiguration.LocationMode.NORMAL;

        LocationClientOption locationClientOption=new LocationClientOption();
        locationClientOption.setCoorType("bd09ll");
        locationClientOption.setIsNeedAddress(true);
        locationClientOption.setOpenGps(true);
        locationClientOption.setScanSpan(1000);

        locationClient.setLocOption(locationClientOption);
        //初始化定位图标
        mIconLocation= BitmapDescriptorFactory.fromResource(R.drawable.arrow);

    }

    /**
     * 初始化地图、设置能够获取自己的位置
     */
    private void initMapView() {
        //获取地图控件引用
        baiduMap=mMapView.getMap();
        //设置能够获取自己的位置
        baiduMap.setMyLocationEnabled(true);
    }

    @Override
    protected void initView(View view) {
        mMapView=view.findViewById(R.id.bmapView);
        initMapView();
        mSearchView=view.findViewById(R.id.map_search_edit);
        mMarker = BitmapDescriptorFactory.fromResource(R.drawable.point);
    }

    @Override
    protected void initEvent() {

        mSearchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s.toString())){
                    return;
                }

                String plantName = s.toString();
                boolean isExit = false;

                for (int i = 0; i < plantModelList.size(); i++) {
                    if (plantModelList.get(i).getPlantCNName().equals(plantName)){
                        isExit = true;
                        Bundle bundle = new Bundle();
                        bundle.putString(Constant.PLANT_NAME,plantName);
                        mSearchView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                IntentUtils.showActivity(getActivity(), PlantDetailActivity.class,bundle);
                            }
                        },3000);

                        break;
                    }
                }

                if (!isExit){
                    Toast.makeText(getActivity(),"搜索的植物不存在",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
        /**
         * 停止定位
         */
        baiduMap.setMyLocationEnabled(false);
        /**
         * 关闭定位
         */
        locationClient.stop();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();


    }
    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length>0){
                    for (int result:grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(getActivity(),
                                    "必须同意所有权限才能使用此程序",
                                    Toast.LENGTH_LONG).show();
//                            finish();
                            return;
                        }
                    }
                    acquireLocation();
                }else {
                    Toast.makeText(getActivity(),
                            "发生未知错误",
                            Toast.LENGTH_LONG).show();
//                    finish();
                }
                break;
            default:
                break;
        }
    }

    private void acquireLocation() {
        initLocation();
        /**
         * 开始定位
         */
        locationClient.start();
    }


    private class MyLocationListener extends BDAbstractLocationListener{

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if (bdLocation.getLocType()==BDLocation.TypeGpsLocation ||
                    bdLocation.getLocType()==BDLocation.TypeNetWorkLocation){
                navigationTo(bdLocation);


            }
        }

    }

    private void navigationTo(BDLocation bdLocation) {
        MyLocationData.Builder builder=new MyLocationData.Builder();
        builder.latitude(bdLocation.getLatitude());
        builder.longitude(bdLocation.getLongitude());
        builder.accuracy(bdLocation.getRadius());
        MyLocationData data=builder.build();

        baiduMap.setMyLocationData(data);

        //设置自定义图标
        MyLocationConfiguration configuration=new
                MyLocationConfiguration(mLocationMode,true,mIconLocation);

        baiduMap.setMyLocationConfiguration(configuration);

        /**
         * 记录最新的位置，更新经纬度
         */
        mLatitude=bdLocation.getLatitude();
        mLongitude=bdLocation.getLongitude();


        if (isFirstLocation){
            LatLng latLng=new LatLng(bdLocation.getLatitude(),bdLocation.getLongitude());

            MapStatusUpdate mapStatusUpdate= MapStatusUpdateFactory.newLatLngZoom(latLng,20f) ;
            baiduMap.animateMapStatus(mapStatusUpdate);


            /*判断baiduMap是已经移动到指定位置*/
            if (baiduMap.getLocationData()!=null)
                if (baiduMap.getLocationData().latitude==bdLocation.getLatitude()
                        &&baiduMap.getLocationData().longitude==bdLocation.getLongitude()){
                    isFirstLocation = false;
                }

        }
    }


}
