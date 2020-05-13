package com.gkftndltek.t_map_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPOIItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapPolygon;
import com.skt.Tmap.TMapView;
import com.skt.Tmap.util.HttpConnect;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Properties;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {
    private String project_key = "l7xx713d4db3b29b418dba74f8af6162f4fb";
    private String secret_key = "8417379e421e4a54800524b24b1db366";
    private TMapPoint tMapPointStart, tMapPointEnd;
    private TMapView tMapView;
    private LinearLayout linearLayoutTmap;
    private String dataUrl;
    public static TMapData.OnResponseCodeInfoCallback responseCodeListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayoutTmap = (LinearLayout)findViewById(R.id.linearLayoutTmap);
        tMapView = new TMapView(this);

        tMapView.setSKTMapApiKey( project_key);
        linearLayoutTmap.addView( tMapView );

        tMapView.setOnClickListenerCallBack(new TMapView.OnClickListenerCallback() {
            @Override
            public boolean onPressEvent(ArrayList<TMapMarkerItem> arrayList, ArrayList<TMapPOIItem> arrayList1, TMapPoint tMapPoint, PointF pointF) {
                return false;
            }

            @Override
            public boolean onPressUpEvent(ArrayList<TMapMarkerItem> arrayList, ArrayList<TMapPOIItem> arrayList1, TMapPoint tMapPoint, PointF pointF) {
                return false;
            }
        });

        TMapMarkerItem markerItem1 = new TMapMarkerItem();

        TMapPoint tMapPoint1 = new TMapPoint(37.570841, 126.985302); // SKT타워

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_launcher_foreground);

        markerItem1.setIcon(bitmap); // 마커 아이콘 지정
        markerItem1.setPosition(0.5f, 1.0f); // 마커의 중심점을 중앙, 하단으로 설정
        markerItem1.setTMapPoint( tMapPoint1 ); // 마커의 좌표 지정
        markerItem1.setName("SKT타워"); // 마커의 타이틀 지정
        markerItem1.setCalloutTitle("하이");
        markerItem1.setCalloutSubTitle("아니 안녕못함;");
        markerItem1.setCanShowCallout(true);
        markerItem1.setAutoCalloutVisible(true);
        tMapView.addMarkerItem("markerItem1", markerItem1); // 지도에 마커 추가
        tMapView.setCenterPoint( 126.985302, 37.570841 );

        TMapData tmapdata = new TMapData();
        String strData = "롯데리아";

        tmapdata.findAllPOI(strData, new TMapData.FindAllPOIListenerCallback() {
            @Override
            public void onFindAllPOI(ArrayList poiItem) {
                for(int i = 0; i < poiItem.size(); i++) {
                    TMapPOIItem item = (TMapPOIItem) poiItem.get(i);
                    Log.d("POI Name: ", item.getPOIName().toString() + ", " +
                            "Address: " + item.getPOIAddress().replace("null", "")  + ", " +
                            "Point: " + item.getPOIPoint().toString());
                }
            }
        });

        tMapPointStart = new TMapPoint(37.570841, 126.985302); // SKT타워(출발지)
                tMapPointEnd = new TMapPoint(37.551135, 126.988205); // 을지로입구역

                tMapView.setCenterPoint( 126.985302, 37.570841 );

        /*
        Properties props = getProperties(this);
        String restUrl = props.getProperty("restUrl");
        dataUrl = restUrl + "/tmap/";

         */

                new Thread(new Runnable(){
                    @Override
                    public void run(){
                        try {
                            TMapPolyLine tMapPolyLine = new TMapData().findPathData(tMapPointStart, tMapPointEnd);
                            tMapPolyLine.setLineColor(Color.RED);
                            tMapPolyLine.setLineWidth(50);
                            System.out.println(tMapPolyLine.getDistance());
                            tMapView.addTMapPolyLine("Line1", tMapPolyLine);
                            //linearLayoutTmap.addView( tMapView );
                        }catch(Exception e) {
                            e.printStackTrace();
                        }
            }
        }).start();

    }

    /*
    public TMapPolyLine findPathData(TMapPoint startpoint, TMapPoint endpoint) throws MalformedURLException, IOException, ParserConfigurationException, FactoryConfigurationError, SAXException {
            Document doc = null;
            TMapPolyLine polyline = new TMapPolyLine();
            StringBuilder uri = new StringBuilder();
            uri.append(dataUrl);
            uri.append("routes?version=1");
            StringBuilder content = new StringBuilder();
            content.append("reqCoordType=WGS84GEO&resCoordType=WGS84GEO&format=xml");
            content.append("&startY=").append(startpoint.getLatitude());
            content.append("&startX=").append(startpoint.getLongitude());
            content.append("&endY=").append(endpoint.getLatitude());
            content.append("&endX=").append(endpoint.getLongitude());
            doc = getDownloadFromPostUrl("findPathData", uri.toString(), content.toString(), false);

            if (doc != null) {
                NodeList list = doc.getElementsByTagName("LineString");

                for(int i = 0; i < list.getLength(); ++i) {
                    Element item = (Element)list.item(i);
                    String str = HttpConnect.getContentFromNode(item, "coordinates");
                    if (str != null) {
                        String[] str2 = str.split(" ");

                        for(int k = 0; k < str2.length; ++k) {
                            try {
                                String[] str3 = str2[k].split(",");
                                TMapPoint point = new TMapPoint(Double.parseDouble(str3[1]), Double.parseDouble(str3[0]));
                                polyline.addLinePoint(point);
                            } catch (Exception var15) {
                            }
                        }
                    }
                }
            }
            return polyline;
    }

    public void setResponseCodeInfoCallBack(TMapData.OnResponseCodeInfoCallback listener) {
        responseCodeListener = listener;
    }

    private static Document getDownloadFromPostUrl(String apiName, String url, String contents, boolean conTypeJason) {
        Document doc = null;
        URLConnection con = HttpConnect.getDownloadFromPostUrl(url, contents, conTypeJason);

        try {
            HttpURLConnection urlCon = (HttpURLConnection)con;
            if (responseCodeListener != null) {
                responseCodeListener.responseCodeInfo(apiName, urlCon.getResponseCode(), url + "/" + contents);
            }

            if (con != null) {
                doc = HttpConnect.getDocument(con);
                if (doc == null && urlCon.getResponseCode() != 200) {
                    if (urlCon.getResponseMessage().equals("Unauthorized")) {
                        Log.w("SKT", "유효하지 않은 API Key 입니다.");
                    } else if (urlCon.getResponseMessage().equals("Bad Request")) {
                        Log.w("SKT", "요청 데이터 오류입니다.");
                    } else {
                        Log.w("SKT", urlCon.getResponseMessage());
                    }
                }
            }

            return doc;
        } catch (IOException var7) {
            return doc;
        }
    }


    private static Properties getProperties(Context context) {
        InputStream inputStream = null;
        try {
            AssetManager assetManager = context.getAssets();
            inputStream = assetManager.open("properties/config.properties");
            Properties props = new Properties();
            props.load(inputStream);
            Properties var4 = props;
            return var4;
        } catch (IOException var14) {
            Log.e("IOException occur", var14.toString());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException var13) {
                    Log.e("IOException occur", var13.toString());
                }
            }

        }
        return null;
    }

     */
}
