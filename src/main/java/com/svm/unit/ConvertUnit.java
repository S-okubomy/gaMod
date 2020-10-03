package main.java.com.svm.unit;

import main.java.com.svm.dto.InitDto;
import main.java.com.svm.dto.InitSettingsDto;

/**
 * 染色体の遺伝子の値を関数の係数にするクラス
 * @author Administrator
 *
 */
public class ConvertUnit {

	public InitDto convert1(InitDto initDto, InitSettingsDto initSettingsDto) {

		double[][] ba1 = new double[initSettingsDto.getIndividualNumber()][initSettingsDto.getMinA().length];
		ba1 = initDto.getBa1();

		double[][] ac1 = new double[initSettingsDto.getIndividualNumber()][initSettingsDto.getMinA().length];
		ac1 = initDto.getAc1();

		for (int i = 0; i < initSettingsDto.getIndividualNumber(); i++) {
			for(int j = 0; j < initSettingsDto.getMinA().length; j++) {
				ac1[i][j]=ba1[i][j];
			}
		}

		initDto.setAc1(ac1);
		return initDto;
	}
}
