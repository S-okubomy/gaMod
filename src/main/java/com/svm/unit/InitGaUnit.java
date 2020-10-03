package main.java.com.svm.unit;

import java.util.Random;

import main.java.com.svm.dto.InitDto;
import main.java.com.svm.dto.InitSettingsDto;


/**
 * 初期遺伝子をつくるクラス
 * @author Administrator
 *
 */
public class InitGaUnit {

    //ランダムーサーチをして，ある程度制約を満たした解を
    //初期個体とする．
    public void initGa(String evaluationClsName, InitDto initDto, InitSettingsDto initSettingsDto) throws Exception{

        //Randomクラスのインスタンス化
        Random rnd = new Random();

        /** 各個体の適応度 初期化 */
        double[][] x1 = new double[initSettingsDto.getIndividualNumber()][initSettingsDto.getMinA().length];
        double[][] ac1 = new double[initSettingsDto.getIndividualNumber()][initSettingsDto.getMinA().length];
        double[][] ba1 = new double[initSettingsDto.getIndividualNumber()][initSettingsDto.getMinA().length];

        for(int i1 =0; i1 < initSettingsDto.getIndividualNumber(); i1++){
            for (int i2 = 0; i2 < initSettingsDto.getMinA().length; i2++) { // 変数の数分
                  x1[i1][i2] = (initSettingsDto.getMaxA()[i2] - initSettingsDto.getMinA()[i2])*(rnd.nextDouble()) + initSettingsDto.getMinA()[i2];    /*乱数を使って実数*/
                  ba1[i1][i2] = x1[i1][i2];    /*バックアップをとる*/
                  ac1[i1][i2] = ba1[i1][i2];
            }
        }

        initDto.setAc1(ac1);
        initDto.setX1(x1);
        initDto.setBa1(ba1);
    }
}
