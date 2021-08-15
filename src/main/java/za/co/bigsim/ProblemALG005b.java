package za.co.bigsim;

public class ProblemALG005b {

	public static void main(String[] args) {
		ProblemALG005b p = new ProblemALG005b();

		System.out.println(p.getString(6, 30, 9));
		
	}
	
	
	private String getString(int sizeOfShape, int xCentre, int yCentre) {
		
		StringBuffer sb = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		int size = sizeOfShape-1;
		int initsize = size;
		int inc = 1;
		for(int k=1; k<25; k++) {
			for(int i=1; i<79; i++) {
				
				if(k == yCentre-initsize ) {
					if(initsize!=0) {
						if(i==xCentre ) {
							sb2.append("$");
						}else if(i==xCentre-inc) {
							sb2.append("$");
						}else if(i==xCentre+inc) {
							sb2.append("$");
							initsize--;
							inc++;
						}else {
							sb2.append("=");
						}
					}else {
						sb2.append("=");
					}
			
									
				}else {
					sb2.append("=");
				}
				
				if(i==xCentre && k == yCentre) {
					sb.append("$");
				}else if((xCentre-size == i && k == yCentre) || (size+xCentre == i && k == yCentre)){
					sb.append("$");					
				}else if((yCentre-size == k && i == xCentre-inc) || (yCentre+size == k && i == xCentre)){
					sb.append("$");	
					inc++;
				}else if(i%10 == 0){
					sb.append(i/10);					
				}else {
					sb.append("=");
				}
				
				
			}
			sb2.append("\n");
		}
		return sb2.toString();
	}

}
