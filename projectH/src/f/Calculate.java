package f;

public class Calculate {
	
	public Calculate()
	{
		
	}
	/**
	風速の基準値を返す関数 
	@param joint ジョイント方式を表す変数(true:外ジョイント,false:スエッジ)
	@param front 間口の広さ
	@param height ビニールハウスの高さ
	@return 風速基準値
	*/
	
	public double windBasis(String conection, double width, double height){
		if(width < 5.7){
			if(conection.equals("外ジョイント接続")) return 30.0;
			else return 18.9;
		}
		else if(width >5.7){
			if(conection.equals("外ジョイント接続")) return 27.0;
			else return 17.01;
		}
		else{
			if(height < 2.55){
				if(conection.equals("外ジョイント接続")) return 30.0;
				else return 18.9;
			}
			else{
				if(conection.equals("外ジョイント接続")) return 27.0;
				else return 17.01;
			}
		}
	}
	
	/**
	積雪量の基準値を返す関数
	@param conection ジョイント方式を表す変数
	@param width 間口の広さ
	@param height ビニールハウスの高さ
	@return 積雪基準値
	*/
	
	public double snowBasis(String conection, double width, double height){
		if(width < 4.95){
			if(conection.equals("外ジョイント接続")) return 23.4;
			else return 17.0;
		}
		else if(width > 6.3){
			if(conection.equals("外ジョイント接続")) return 18.1;
			else return 13.8;
		}
		else if(width == 4.95){
			if(conection.equals("外ジョイント接続")){
				if(height < 2.6) return 23.4;
				else return 23.2;
			}
			else{
				if(height <=2.4) return 17.0;
				else return 17.3;
			}
		}
		else if(width == 6.3){
			if(conection.equals("外ジョイント接続")){
				if(height < 3.2) return 23.2;
				else return 18.1;
			}
			else{
				if(height < 3.0) return 17.3;
				else return 13.8;
			}
		}
		else{
			if(conection.equals("外ジョイント接続")) return 23.2;
			else return 17.3;
		}
	}
	
	/**
	耐久係数を計算する関数
	@param year 経過月数
	@return 耐久係数
	*/
	
	public double dFactor(int year){
		double factor, num;
		num = 1 + 13*(double)year*12/4800;
		factor = 1 - Math.log10(num);
		return factor;
	}
	
	
	/**
	データを比較し危険があるかを求める関数
	@param conection ジョイント方式を表す変数
	@param width 間口の広さ
	@param height ビニールハウスの高さ
	@param year 経過年数
	@param wind 風速
	@param snow 積雪量
	@return 危険予測(0:危険無し,1:雪の危険あり,2:風の危険あり,3:風と雪の危険あり)
	*/
	
	public int compare(String conection, double width, double height, int year, double wind, double snow){
		double windNum = dFactor(year) * windBasis(conection,width,height);
		double snowNum = dFactor(year) * snowBasis(conection,width,height);
		
		if(wind < windNum){
			if(snow < snowNum){
				return 0;
			}
			else return 1;
		}
		else {
			if(snow < snowNum){
				return 2;
			}
			else return 3;
		}
		
	}

}
