package main.java.com.svm.evalutePack;
import main.java.com.svm.dto.InitSettingsDto;
import main.java.com.svm.interfaceEva.BaseEvaVal;

/**
 * @author Administrator
 *
 */
public class Test1EvaClass implements BaseEvaVal{
    /* (非 Javadoc)
     * @see interfaceEva.BaseEvaVal#execute(double)
     */
	@Override
    public double execute(double[] gaParameter, String modeName){
    	// f(x)=x4−20x2+20xf(x)=x4−20x2+20xの例  →マイナスをつけて最小値が適用度が最も高くする。
    	double evaValue = -(Math.pow(gaParameter[0],4) - 20.0*Math.pow(gaParameter[0], 2) + 20*gaParameter[0]);
    	return evaValue;
    }

	@Override
	public void setInitSettingsDto(InitSettingsDto initSettingsDto) {
		// 個体数
		initSettingsDto.setIndividualNumber(100);
		// 計算世代数
		initSettingsDto.setCalSedai(1000);
		// 変数の最小値
		double[] minA = {-100.0d};
		initSettingsDto.setMinA(minA);
		// 変数の最大値
		double[] maxA = {100.0d};
		initSettingsDto.setMaxA(maxA);
		
	}
}
