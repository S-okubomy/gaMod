package main.java.com.svm.dto;

import java.io.Serializable;


/**
 * 初期遺伝子をつくる際に使用する
 * Javabeans
 * @author t_okubomy
 */
public class InitDto implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /** x1[][]:染色体 */
    private static double[][] x1;  //共通変数にする

    /** ba1[][]:染色体のバックアップ */
    private static double[][] ba1; //共通変数にする

    /** ac[][]:目的関数の係数 */
    private static double[][] ac1; //共通変数にする

    /** 重み係数 ω(プロジェクト固有） **/
    private static int[] weightParam;  //共通変数にする

    public InitDto() {
    }

    public void setX1(double[][] x1){
        InitDto.x1 = x1;
    }

    public void setBa1(double[][] ba1){
        InitDto.ba1 = ba1;
    }

    public void setAc1(double[][] ac1){
        InitDto.ac1 = ac1;
    }

    public void setWeightParam(int[] weightParam){
        InitDto.weightParam = weightParam;
    }


    /** 染色体を返すゲッター　*/
    public double[][] getX1() {
        return InitDto.x1;
    }

    /** 染色体のバックアップを返すゲッター　*/
    public double[][] getBa1() {
        return InitDto.ba1;
    }

    /** 目的関数の係数を返すゲッター　*/
    public double[][] getAc1() {
        return InitDto.ac1;
    }

    public int[] getWeightParam() {
        return InitDto.weightParam;
    }
}
