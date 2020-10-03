package main.java.com.svm.unit;

import java.util.Random;

import main.java.com.svm.dto.InitDto;
import main.java.com.svm.dto.InitSettingsDto;


/**
 * ブレンド交差をするクラス
 * @author Administrator
 *
 */
public class CrossBLXUnit {

	public InitDto crossBLX(InitDto initDto, InitSettingsDto initSettingsDto) {
		//Randomクラスの生成
        Random r = new Random();
        Random rnd = new Random();
        int se1;
        int se2;

		double[][] x1 = new double[initSettingsDto.getIndividualNumber()][initSettingsDto.getMinA().length];
		x1 = initDto.getX1();
		double[][] ba1 = new double[initSettingsDto.getIndividualNumber()][initSettingsDto.getMinA().length];
		ba1 = initDto.getBa1();

		double[] MAXap = new double[initSettingsDto.getMinA().length];
		double[] MINap = new double[initSettingsDto.getMinA().length];

//		if(rr <= 5) {         /*②s* 交叉をするかどうか*/
			for (int ci = 0; ci < (int)Math.ceil((initSettingsDto.getIndividualNumber())/5); ci++) {
				se1 = r.nextInt(initSettingsDto.getIndividualNumber()) ; // 交叉する染色体を決める
				if(se1 == initSettingsDto.getIndividualNumber() - 1) {         /*染色体が最終のものを選択*/
					se2 = 0;
				}
				else{
					se2 = se1 + 1;
				}
				for(int j = 0; j < initSettingsDto.getMinA().length; j++){
					/*--大きい方と小さい方を区別--*/
					if(x1[se1][j] > x1[se2][j]) {
						MAXap[j] = x1[se1][j];
						MINap[j] = x1[se2][j];
					}
					else{   /*-両方とも等しければ，どちらでも-*/
						MAXap[j] = x1[se2][j];
						MINap[j] = x1[se1][j];
					}
					x1[se1][j]=(MAXap[j]-MINap[j]) * (rnd.nextDouble()) + MINap[j];    /*乱数を使って子供に置き換える*/
					x1[se2][j]=(MAXap[j]-MINap[j]) * (rnd.nextDouble()) + MINap[j];    /*乱数を使って子供に置き換える*/

					 ba1[se1][j] = x1[se1][j];   /*代入したものをバック*/
					 ba1[se2][j] = x1[se2][j];
				}
//				System.out.println("交叉個体No.: " + se1);
			}
			initDto.setX1(x1);
			initDto.setBa1(ba1);
//		}  /*②f*  if(ko<=5)の終わり*/

		return initDto;
	}
}
