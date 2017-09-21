package tw.com.triplei.service;

import org.springframework.stereotype.Service;

@Service
public class IRRCaculator {

	public double getRemuneration(double period, double times, double premium, double expired) { //算淨報酬

		double remuneration = 0.0;
		if (period <= times) {

			remuneration = expired - (period * premium);

		} else if (period > times) {

			remuneration = expired - (times * premium);

		}

		return remuneration;

	}
	
	public double getTotal(double period, double times, double premium) { //算總繳金額
		
		double total = 0.0;
		if (period <= times) {
			
			total = period * premium;
			
		} else if (period > times) {
			
			total = times * premium;
			
		}
		
		return total;
		
	}
	
	
	/* *
	 * IRR計算機-計算概念
	 * 
	 * 由四個變數可計算 : 繳費年期、領回時間、年繳保費、期滿領回
	 * 
	 * EX : 每年存1000，繳費期間3年，第3年末可領回5000
	 * 計算公式	5000=1000*(1+irr)^3+(1+irr)^2+(1+irr)^1
	 * 			5000=1000*[(1+irr)^3+(1+irr)^2+(1+irr)^1]
	 * 			5/1=(1+irr)^3+(1+irr)^2+(1+irr)^1
	 * 
	 * Step.1  判斷民眾輸入之變數如何做最適當之處置
	 * Step.2  使用for迴圈長出降冪次方疊加
	 * Step.3 irr以0先帶入公式計算出一數(程式碼中以result呈現)，若不大於5/1此移項數字(程式碼中以fraction代表)即加萬分之一繼續計算
	 * 
	 * */

	public double getIRR(double period, double times, double premium, double expired) {

		// 繳費年期 : period
		// 領回時間 : times
		// 年繳保費 : premium
		// 期滿領回 : expired

		double remuneration = expired - (period * premium); // 淨報酬
		double irr = 0.0; // IRR起始
		double result = 0.0; //
		double fraction = (expired / premium);

		if (period == 1 && remuneration >= 0) { // 躉繳判斷

			while (fraction > result) {

				result = 0.0; // 歸0避免壘加
				result += Math.pow(1 + irr, times);
				irr += 0.00001;
			}

			return irr;

		} else if (period == 1 && remuneration < 0) { // 躉繳負值

			while (fraction > result) {

				result = 0.0;
				result += Math.pow(irr, times);
				irr += 0.00001;
			}

			return irr - 1;

		} else if (times >= period && remuneration >= 0) { // 正常情況下的IRR(期滿領回或期滿過後才領回)

			while (fraction > result) {

				result = 0.0;
				for (double i = times; i > (times - period); i--) {
					result += Math.pow(1 + irr, i);
				}
				irr += 0.00001;
			}

			return irr;

		} else if (times >= period && remuneration < 0) { // 沒有提早+淨報酬為負

			while (fraction > result) {

				result = 0.0;
				for (double i = times; i > (times - period); i--) {
					result += Math.pow(irr, i);
				}
				irr += 0.00001;
			}

			return irr - 1;

		} else if (times < period && (times * premium) - expired <= 0) { // 提早領回，淨報酬為正

			while (fraction > result) {

				result = 0.0;
				for (double i = times; i > 0; i--) {
					result += Math.pow(1 + irr, i);
				}
				irr += 0.00001;
			}

			return irr;

		} else if (times < period && (times * premium) - expired > 0) { // 提早領回，淨報酬為負

			while (fraction > result) {

				result = 0.0;
				for (double i = times; i > 0; i--) {
					result += Math.pow(irr, i);
				}
				irr += 0.00001;
			}

			return irr - 1;
		}
		return irr;
	}
}