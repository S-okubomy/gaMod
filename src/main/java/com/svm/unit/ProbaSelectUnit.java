package main.java.com.svm.unit;

import main.java.com.svm.dto.CalFitDto;
import main.java.com.svm.dto.CalProbaDto;
import main.java.com.svm.dto.InitSettingsDto;

/**
 * 選択確立を計算するクラス
 * @author Administrator
 *
 */
public class ProbaSelectUnit {

    //ランダムーサーチをして，ある程度制約を満たした解を
    //初期個体とする．
    public CalProbaDto probaSelecct(CalFitDto calFitDto, InitSettingsDto initSettingsDto){

        /** 選択確率 */
        double ps;
        /** 選択確率の総和 */
        double sumPs;
        /**選択確率の保存用 */
        double[] rpsn = new double[initSettingsDto.getIndividualNumber() + 1];

        /** 各個体の適応度 初期化 */
        double[] fit1 = new double[initSettingsDto.getIndividualNumber()];
        fit1 = calFitDto.getFit1();
        
        /** 適応度の総和 */
        double sumFit = calFitDto.getSumFit();
        
        sumPs=0;
        rpsn[0]=0;
        for(int i=0; i< initSettingsDto.getIndividualNumber(); i++){
            ps=(fit1[i] /sumFit)*100d;    /*選択確率の計算*/
            sumPs=sumPs+ps;      /*総和を計算*/
            rpsn[i+1]=sumPs;    /*記憶*/
        }
        
        CalProbaDto calProbaDto = new CalProbaDto();
        calProbaDto.setRpsn(rpsn);
        
        return calProbaDto;
    }
}
