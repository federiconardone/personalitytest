package pckMaths;

/*
 * Given an array of the value chosen by the user, this class return
 * in according with TIPI system the corresponding 5 values of
 * the personality profile.
 */
public class Converter {
	
	public static Float[] getConvertedProfile (int [] selectedProfile){
		
		Float normalizedProfile [] = new Float[5];
		Float tempValue = null;
		
		for (int i = 1; i < 6; i ++) {
			
			switch(i) {
			
			case 1:
				tempValue=Averager(selectedProfile[0], Inverter(selectedProfile[5]));
				break;
			case 2:
				tempValue=Averager(Inverter(selectedProfile[1]), selectedProfile[6]);
				break;
			case 3:
				tempValue=Averager(selectedProfile[2], Inverter(selectedProfile[7]));
				break;
			case 4:
				tempValue=Averager(Inverter(selectedProfile[3]), selectedProfile[8]);
				break;
			case 5:
				tempValue=Averager(selectedProfile[4], Inverter(selectedProfile[9]));
				break;
				
			}
			normalizedProfile [i - 1] = tempValue;
		}
		
		return normalizedProfile;
	}
	
	//Simply, reverses the value for some personality properties
	private static int Inverter (int givenValue) {
		
		return (8-givenValue);
	}
	
	//Calculate the average
	private static Float Averager (int firstValue, int secondValue) {
		
		return (((float)firstValue + (float)secondValue)) / 2;
	}
}
