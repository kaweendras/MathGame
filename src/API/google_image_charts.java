/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import java.net.URL;

/**
 *
 * @author Yashodha Hansimali Godage 
 * 
 */
public class google_image_charts {
    
    /**
     * Send the parameters with the URL to get the Image chart
     * from the API
     * @param d1 data1
     * @param d2 data2
     * @param d3 data3
     * @param d4 data4
     * @param v1 caption1
     * @param v2 caption2
     * @param v3 caption3
     * @param v4 caption4
     * @return Image URL
     * 

     */
    public String getApi(String d1,String d2,String d3,String d4,String v1,String v2,String v3,String v4){
        String api = "https://chart.googleapis.com/chart?chs=500x200&chd=t:"+d1+","+d2+","+d3+","+d4+"&cht=p3&chl="+d1+"|"+d2+"|"+d3+"|"+d4+"";
        return api;
    }

   
    
}
