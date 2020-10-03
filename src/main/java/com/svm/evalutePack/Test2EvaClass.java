package main.java.com.svm.evalutePack;
import main.java.com.svm.dto.InitSettingsDto;
import main.java.com.svm.interfaceEva.BaseEvaVal;

/**
 * @author Administrator
 *
 */
public class Test2EvaClass implements BaseEvaVal{
    /* (非 Javadoc)
     * @see interfaceEva.BaseEvaVal#execute(double)
     */
    @Override
    public double execute(double[] gaParameter, String modeName){
        // マイナスをつけて最小値が適用度が最も高くする。
        double evaValue = -(Math.pow(gaParameter[0]-1,2) + Math.pow(gaParameter[1]-2, 2));
        return evaValue;
    }

    @Override
    public void setInitSettingsDto(InitSettingsDto initSettingsDto) {
        // 個体数
        initSettingsDto.setIndividualNumber(100);
        // 計算世代数
        initSettingsDto.setCalSedai(1000);
        // 変数の最小値
        double[] minA = {-10.0d, -10.0d};
        initSettingsDto.setMinA(minA);
        // 変数の最大値
        double[] maxA = {10.0d, 10.0d};
        initSettingsDto.setMaxA(maxA);
        
    }
}
