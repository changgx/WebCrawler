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
					// ��������
					String gameNo = tds.get(0).text();
					String week = gameNo.substring(0, 2);
					String no = gameNo.substring(2, gameNo.length());
					System.out.println("ganmeNo:"+gameNo);
					System.out.println("week:"+week);
					System.out.println("no:"+no);

					// ���±�����ɫ
					String color = tds.get(1).attr("bgcolor");
					System.out.println("color:"+color);

					// ��������
					String gameName = tds.get(1).text();
					System.out.println("gameName:"+gameName);

					// �����ִ�
					String gameRound = tds.get(2).text();
					System.out.println("gameRound:"+gameRound);

					// ����ʱ��
					String gameTime = tds.get(3).text();
					System.out.println("gameTime:"+gameTime);
					
					//����״̬
					String status = tds.get(4).text();
					System.out.println("status:"+status);
					
					//������Ϣ
					String homeTemp = tds.get(5).text();
					//����
					String gray = tds.get(5).getElementsByClass("gray").text();
					//��������
					String homeTeam = tds.get(5).getElementsByTag("a").text();
					//���ӻ���
					String homeYellowcard = tds.get(5).getElementsByClass("yellowcard").text();
					//�Ͷӻ�Ƭ
					String homeRedcard = tds.get(5).getElementsByClass("redcard").text();
					System.out.println("homeTemp:"+homeTemp);
					System.out.println("gray:"+gray);
					System.out.println("homeTeam:"+homeTeam);
					System.out.println("homeYellowcard:"+homeYellowcard);
					System.out.println("homeRedcard:"+homeRedcard);
					
					//�ȷ�
					String bf = tds.get(6).text();
					System.out.println("bf:"+bf);
					//���ӽ���
					String zbf = tds.get(6).getElementsByClass("clt1").text();
					System.out.println("zbf:"+zbf);
					//�����̿�
					String odds = tds.get(6).getElementsByClass("fhuise").text();
					System.out.println("odds:"+odds);
					//�Ͷӽ���
					String kbf = tds.get(6).getElementsByClass("clt3").text();
					System.out.println("kbf:"+kbf);
					
					//�Ͷ���Ϣ
					String visitingTemp = tds.get(7).text();
					//����
					String visitGray = tds.get(7).getElementsByClass("gray").text();
					//��������
					String visitingTeam = tds.get(7).getElementsByTag("a").text();
					//���ӻ���
					String visitYellowcard = tds.get(7).getElementsByClass("yellowcard").text();
					//�Ͷӻ�Ƭ
					String visitRedcard = tds.get(7).getElementsByClass("redcard").text();
					System.out.println("visitingTemp:"+visitingTemp);
					System.out.println("visitGray:"+visitGray);
					System.out.println("visitingTeam:"+visitingTeam);
					System.out.println("visitYellowcard:"+visitYellowcard);
					System.out.println("visitRedcard:"+visitRedcard);
					
					//�볡�ȷ�
					String bcbf = tds.get(8).text();
					System.out.println("bcbf:"+bcbf);
					
					//����
					String amidithion = tds.get(10).text();
					System.out.println("amidithion:"+amidithion);
				

				}
				

			}
			

		} catch (Exception e) {

		}

	}
}
