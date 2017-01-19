package cn.com.jd.javabase.Advance.annotation;
public class EnumTest {
	public static void main(String[] args) {
		System.out.println(ThrafficLamp.GREEN.nextLamp());
	}
	public enum WeekDay{
		SUN(),MON(1),TUS,WED,THI,FRI,SAT;
		private WeekDay(){}
		private WeekDay(int day){}
		
	}
	public enum ThrafficLamp{
		RED(){
		public ThrafficLamp nextLamp() {
			return GREEN;
		}},
		YELLOW(45){
			public ThrafficLamp nextLamp(){
				return RED;
			}
		},
		GREEN(5){
			public ThrafficLamp nextLamp() {				
				return YELLOW;
			}
		};
		public abstract ThrafficLamp nextLamp();
		private  int time;
		private ThrafficLamp(){}
		private ThrafficLamp(int time){
			this.time=time;
		}
		//可以覆盖 toString
		public String toString(){
			return this.time+"";
		}
	}
}
