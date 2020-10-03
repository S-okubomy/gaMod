package main.java.com.svm.dto;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import main.java.com.svm.interfaceEva.BaseEvaVal;


/**
 * 初期遺伝子をつくる際に使用する
 * Javabeans
 * @author t_okubomy
 */
public class InitSettingsDto implements Serializable {

	private static final long serialVersionUID = 1L;
    
    // 変数の最小値
	private double[] minA;
    // 変数の最大値
	private double[] maxA;

	private int individualNumber;              //個体数
	private int calSedai;       //計算世代数
	
//	public final int nn = minA.length;       //染色体の数
//	public final int NN = individualNumber;       //染色体の数
//	public final int CAL_SEDAI = calSedai;       //染色体の数
//	
//	public final double[] MIN_A = minA;       //染色体の数
//	public final double[] MAX_A = maxA;       //染色体の数

	public InitSettingsDto(String evaluationClsName) throws Exception {
		setInitSettingsDto(evaluationClsName, this);
	}
	
	public void setIndividualNumber(int individualNumber){
		this.individualNumber = individualNumber;
    }
	
	public int getIndividualNumber() {
		return this.individualNumber;
	}
	
	public void setCalSedai(int calSedai){
		this.calSedai = calSedai;
    }
	
	public int getCalSedai() {
		return this.calSedai;
	}
	
	public void setMinA(double[] minA){
		this.minA = minA;
    }
	
	public double[] getMinA() {
		return this.minA;
	}
	
	public void setMaxA(double[] maxA){
		this.maxA = maxA;
    }
	
	public double[] getMaxA() {
		return this.maxA;
	}
	
	private void setInitSettingsDto(String evaluationClsName, InitSettingsDto initSettingsDto) throws Exception {
		
		try {
    		// クラス名からクラスのインスタンスを取得する。
			Class<? extends BaseEvaVal> evaluationCls 
			    = Class.forName(evaluationClsName).asSubclass(BaseEvaVal.class);
			Method method = evaluationCls.getMethod("setInitSettingsDto", InitSettingsDto.class);
			method.invoke(evaluationCls.newInstance(), initSettingsDto);
		} catch (ClassCastException e) {
            throw new Exception("評価クラスにはBaseEvaValを実装したクラスを指定する必要があります。", e);
        } catch (ClassNotFoundException e) {
            throw new Exception("評価クラスが見つかりません。");
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
            e.printStackTrace();
        }
	}
}
