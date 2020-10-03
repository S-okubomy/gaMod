package main.java.com.svm.unit;

import java.util.Random;

import main.java.com.svm.dto.InitDto;
import main.java.com.svm.dto.InitSettingsDto;

/**
 * 突然変異をするクラス
 * @author Administrator
 *
 */
public class MutationUnit {

    public InitDto mutation(InitDto initDto, InitSettingsDto initSettingsDto) {

        /*突然変異*/
        Random rSe = new Random();
        Random rnd = new Random();

        double[][] x1 = new double[initSettingsDto.getIndividualNumber()][initSettingsDto.getMinA().length];
        x1 = initDto.getX1();
        double[][] ba1 = new double[initSettingsDto.getIndividualNumber()][initSettingsDto.getMinA().length];
        ba1 = initDto.getBa1();

        /* 個体のうち 10% 突然変異を実行する*/
        for(int i=0; i< initSettingsDto.getIndividualNumber() /7; i++){
            int se1 = rSe.nextInt(initSettingsDto.getIndividualNumber() -1) + 1;   /* 0番目はエリート遺伝子用  個体を決定*/
            for (int i2 = 0; i2 < initSettingsDto.getMinA().length; i2++) { // 変数の数分
                  x1[se1][i2]=(initSettingsDto.getMaxA()[i2] - initSettingsDto.getMinA()[i2])*(rnd.nextDouble()) + initSettingsDto.getMinA()[i2];    /*乱数を使って実数*/
                  ba1[se1][i2] = x1[se1][i2];
            }
        }

        initDto.setX1(x1);
        initDto.setBa1(ba1);

        return initDto;
    }
}
