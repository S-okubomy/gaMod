package main.java.com.svm.service;

import main.java.com.svm.dto.ResultGeneDto;
import static main.java.com.svm.util.FmtUtil.dblToStr;

import main.java.com.svm.evalutePack.EvaCalMax1;
import main.java.com.svm.unit.MainRcgaUnit;


public class GetWeightValService {

    // SVM実行モードにより変更する。
    public static final SVM_MODE svmExeMode = SVM_MODE.INDEPENDENT_ANS;
    
    public static void main(String[] args) throws Exception {
        
        // SVMの実行モードにより、学習モード（解析パターン）を切り替える
        switch (svmExeMode) {
        case ONLY_QUE:
            // ================= 質問解析用の重み係数を作成する  =================
            System.out.println("質問解析のみ");
            getWeightVal("que");
            System.out.println("質問解析のみ 完了");
            break;
        case ONLY_ANS:
            // ================= 応答解析用の重み係数を作成する  =================
            System.out.println("応答解析のみ");
            getWeightVal("ans");
            System.out.println("応答解析のみ 完了");
            break;
        case QUE_AND_ANS:
            // ================= 質問解析及び応答解析用の重み係数を作成する  =================
            System.out.println("質問解析及び応答解析");
            getWeightVal("que");
            System.out.println("質問解析  完了");
            getWeightVal("ans");
            System.out.println("応答解析  完了");
            break;
        case INDEPENDENT_ANS:
            // ================= 質問解析及び応答解析用の重み係数を作成する  =================
            System.out.println("単独の応答解析のみ");
            getWeightVal("independentAns");
            System.out.println("単独の応答解析  完了");
            break;
        default:
            System.out.println("SVM実行モードが該当しません。実行モードを確認してください");
            break;
        }
    }
    
    private static void getWeightVal(String modeName) throws Exception {
        
        // 最適化する評価関数名（クラス名）
        String evalMethodName = "main.java.com.svm.evalutePack.Test2EvaClass"; // 質問パターンの重み係数算出用
        
        //0.5秒待つ
        Thread.sleep( 500 ) ;
        
        // ================= 重み係数を作成する  =================
        MainRcgaUnit mainRcga = new MainRcgaUnit();
        ResultGeneDto result = mainRcga.calGene(evalMethodName, modeName);
        System.out.println("--------------最大値の重み係数結果を再計算 （" + modeName + "）------------------");
        EvaCalMax1 evaCalMax1 = new EvaCalMax1();
        evaCalMax1.evaCalMax(evalMethodName, result.getAc1(), modeName);
        
        System.out.println("---------------------最適化結果（" + modeName + "）------------------");
        String strPrMaxAll = "実値: " + dblToStr(result.getTrueVal1()) +  "  係数： ";
        for (int i = 0; i < result.getAc1().length; i++) {
            strPrMaxAll = strPrMaxAll + dblToStr(result.getAc1()[i])  + "  ";
        }
        
        //0.5秒待つ
        Thread.sleep( 500 ) ;
        System.out.println(strPrMaxAll);
        
        //0.5秒待つ
        Thread.sleep( 500 ) ;
    }
    
    /**
     * SVM実行モード
     * @author Administrator
     */
    public static enum SVM_MODE {

        ONLY_QUE(1),
        ONLY_ANS(2),
        QUE_AND_ANS(3),
        INDEPENDENT_ANS(4);
        
        int mode;
        SVM_MODE(int mode) {
            this.mode = mode;
        }

        static SVM_MODE get(int mode) {
            for (SVM_MODE svmMode : values()) {
                if (svmMode.mode == mode) {
                    return svmMode;
                }
            }
            return null;
        }
    }

}
