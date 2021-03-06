package main.java.com.svm.unit;

import java.util.Random;

import main.java.com.svm.dto.CalFitDto;
import main.java.com.svm.dto.CalProbaDto;
import main.java.com.svm.dto.InitDto;
import main.java.com.svm.dto.InitSettingsDto;

/**
 * 選択をするクラス
 * @author Administrator
 *
 */
public class RouletteSelectUnit {

    //ルーレット選択
    public InitDto selectR(CalProbaDto calProbaDto, CalFitDto calFitDto, InitSettingsDto initSettingsDto){

        //Randomクラスの生成
        Random r = new Random();

        /**選択確率の保存用 */
        double[] rpsn = new double[initSettingsDto.getIndividualNumber() + 1];

        InitDto initDto = new InitDto();
        double[][] x1 = new double[initSettingsDto.getIndividualNumber()][initSettingsDto.getMinA().length];
        x1 = initDto.getX1();
        double[][] ba1 = new double[initSettingsDto.getIndividualNumber()][initSettingsDto.getMinA().length];
        ba1 = initDto.getBa1();

        //エリート選択用
        double[][] baMax1 = new double[initSettingsDto.getIndividualNumber()][initSettingsDto.getMinA().length];
        baMax1 = ba1;

        //個体の多様性維持のため、選択確率の上限を30%に変換する
        /** 適応度の総和 上限修正前 */
        double sumFitBefore = calFitDto.getSumFit();

        /** 選択確率 */
        double ps;
        /** 選択確率の総和 */
        double sumPs;

        //エリート選択用（適応度が最大のものを保存）
        double[] fit1 = new double[initSettingsDto.getIndividualNumber()];
        fit1 = calFitDto.getFit1();

          sumPs=0;
          rpsn[0]=0;
          for(int i=0; i< initSettingsDto.getIndividualNumber(); i++){
              ps=(fit1[i] /sumFitBefore)*100d;    /*選択確率の計算*/
              if (ps > 3.0) {
                  ps = 3.0;   //個体の多様性維持のため、選択確率の上限を30%に変換する
              }
              sumPs=sumPs+ps;        /*総和を計算*/
            rpsn[i+1]=sumPs;      /*記憶*/
          }

        for(int i=0; i < initSettingsDto.getIndividualNumber();i++){       /*個体数分だけルーレットする*/
            int rr = r.nextInt((int)rpsn[initSettingsDto.getIndividualNumber()]);      //0～ (総和-1)の乱数を取得する
            for(int j = 0; j < initSettingsDto.getIndividualNumber(); j++){
                if((rpsn[j] <= rr) && (rr < rpsn[j+1])) {  /* rpsn[j]<rr<rpsn[j+1]なら，選択される*/
                  for(int k = 0; k < initSettingsDto.getMinA().length; k++){
                      x1[i][k]=ba1[j][k];    /*上の条件を満たすのは個体j番目だからそれを代入*/
                  }
                }
            }
          }

        for(int i = 0; i < initSettingsDto.getIndividualNumber(); i++) {
             for(int j = 0; j < initSettingsDto.getMinA().length; j++){
                ba1[i][j]=x1[i][j];    /*バックアップ*/
             }
        }

        double fitMax = fit1[0];
        int fitMaxNum = 0;
        for(int i =1; i < fit1.length ; i++) {
            if (fit1[i] > fitMax) {
                fitMax = fit1[i];
                fitMaxNum = i;
            }
        }

         for(int j = 0; j < initSettingsDto.getMinA().length; j++){
            x1[0][j]=baMax1[fitMaxNum][j];    /*バックアップ*/
            ba1[0][j]=baMax1[fitMaxNum][j];    /*バックアップ*/
         }

        initDto.setX1(x1);
        initDto.setBa1(ba1);
        
        return initDto;
    }
}
