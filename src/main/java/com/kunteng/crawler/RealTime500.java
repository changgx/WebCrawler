package com.kunteng.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class RealTime500 {

	public static void main(String[] args) {

		try {
			Document doc = Jsoup.connect("http://live.500.com/?e=2016-12-09").get();

			Elements trs = doc.getElementById("table_match").select("tr");

			for (int i = 1; i <= trs.size(); i++) {
				Elements tds = trs.get(i).select("td");
				int size = tds.size();
				if (0 < size) {
					// 比赛名次
					String gameNo = tds.get(0).text();
					String week = gameNo.substring(0, 2);
					String no = gameNo.substring(2, gameNo.length());
					System.out.println("ganmeNo:"+gameNo);
					System.out.println("week:"+week);
					System.out.println("no:"+no);

					// 赛事背景颜色
					String color = tds.get(1).attr("bgcolor");
					System.out.println("color:"+color);

					// 赛事名称
					String gameName = tds.get(1).text();
					System.out.println("gameName:"+gameName);

					// 赛事轮次
					String gameRound = tds.get(2).text();
					System.out.println("gameRound:"+gameRound);

					// 赛事时间
					String gameTime = tds.get(3).text();
					System.out.println("gameTime:"+gameTime);

					//赛事状态
					String status = tds.get(4).text();
					System.out.println("status:"+status);

					//主队信息
					String homeTemp = tds.get(5).text();
					//排名
					String gray = tds.get(5).getElementsByClass("gray").text();
					//主队名称
					String homeTeam = tds.get(5).getElementsByTag("a").text();
					//主队黄牌
					String homeYellowcard = tds.get(5).getElementsByClass("yellowcard").text();
					//客队簧片
					String homeRedcard = tds.get(5).getElementsByClass("redcard").text();
					System.out.println("homeTemp:"+homeTemp);
					System.out.println("gray:"+gray);
					System.out.println("homeTeam:"+homeTeam);
					System.out.println("homeYellowcard:"+homeYellowcard);
					System.out.println("homeRedcard:"+homeRedcard);

					//比分
					String bf = tds.get(6).text();
					System.out.println("bf:"+bf);
					//主队进球
					String zbf = tds.get(6).getElementsByClass("clt1").text();
					System.out.println("zbf:"+zbf);
					//赔率盘口
					String odds = tds.get(6).getElementsByClass("fhuise").text();
					System.out.println("odds:"+odds);
					//客队进球
					String kbf = tds.get(6).getElementsByClass("clt3").text();
					System.out.println("kbf:"+kbf);

					//客队信息
					String visitingTemp = tds.get(7).text();
					//排名
					String visitGray = tds.get(7).getElementsByClass("gray").text();
					//主队名称
					String visitingTeam = tds.get(7).getElementsByTag("a").text();
					//主队黄牌
					String visitYellowcard = tds.get(7).getElementsByClass("yellowcard").text();
					//客队簧片
					String visitRedcard = tds.get(7).getElementsByClass("redcard").text();
					System.out.println("visitingTemp:"+visitingTemp);
					System.out.println("visitGray:"+visitGray);
					System.out.println("visitingTeam:"+visitingTeam);
					System.out.println("visitYellowcard:"+visitYellowcard);
					System.out.println("visitRedcard:"+visitRedcard);

					//半场比分
					String bcbf = tds.get(8).text();
					System.out.println("bcbf:"+bcbf);

					//赛果
					String amidithion = tds.get(10).text();
					System.out.println("amidithion:"+amidithion);


				}


			}


		} catch (Exception e) {

		}

	}
}
