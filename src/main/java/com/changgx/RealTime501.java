package com.changgx;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class RealTime501 {

    public static void main(String[] args) {

        try {
            Document doc = Jsoup.connect("http://a.haocai138.com/info/match/Jingcai.aspx?date=2016-12-08").get();

            Elements trs = doc.getElementById("MatchTable").select("tr");

            for (int i = 1; i < trs.size(); i++) {

                Elements tds = trs.get(i).select("td");
                int size = tds.size();
                if (1 < size) {
                    // 比赛场次
                    String gameNo = tds.get(0).text();
                    String week = gameNo.substring(0, 2);
                    String no = gameNo.substring(2, gameNo.length());
                    System.out.println("ganmeNo:" + gameNo);
                    System.out.println("week:" + week);
                    System.out.println("no:" + no);

                    // 赛事背景颜色
                    String background_color = tds.get(1).attr("style").split("background-color:")[1];
                    System.out.println("color:" + background_color.substring(0, background_color.length() - 1));

                    // 赛事名称
                    String gameName = tds.get(1).text();
                    System.out.println("gameName:" + gameName);

//					// 赛事轮次
//					String gameRound = tds.get(2).text();
//					System.out.println("gameRound:"+gameRound);

                    // 赛事时间
                    String gameTime = tds.get(2).text();
                    System.out.println("gameTime:" + gameTime);

                    //赛事状态
                    String status = tds.get(3).text();
                    System.out.println("status:" + status);

                    //主队信息
                    String homeTemp = tds.get(4).text();

                    //主队名称
                    String homeTeam = tds.get(4).text().split("\\(")[0];


                    System.out.println("homeTemp:" + homeTemp);

                    System.out.println("homeTeam:" + homeTeam);


                    //比分
                    String bf = tds.get(5).text();
                    System.out.println("bf:" + bf);
                    //主队进球
                    String zbf = "";
                    //客队进球
                    String kbf = "";
                    if (bf.split("\\-").length == 2) {
                        zbf = bf.split("\\-")[0];
                        kbf = bf.split("\\-")[1];
                    }
                    System.out.println("zbf:" + zbf);
                    System.out.println("kbf:" + kbf);


                    //客队信息
                    String visitingTemp = tds.get(6).text();

                    //客队名称
                    String visitingTeam = tds.get(6).text().split("\\(")[0];

                    System.out.println("visitingTemp:" + visitingTemp);

                    System.out.println("visitingTeam:" + visitingTeam);


                    //半场比分
                    String bcbf = tds.get(7).text();
                    System.out.println("bcbf:" + bcbf);

//					//赛果
//					String amidithion = tds.get(11).text();
//					System.out.println("amidithion:"+amidithion);


                }


            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
