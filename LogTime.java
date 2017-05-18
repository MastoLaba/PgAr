package problemi;

import java.time.Instant;
import java.util.ArrayList;

/**
 * @author RAD
 *
 */
public class LogTime implements TimeStamp {
	ArrayList<String>timestamp;
	public LogTime(){
		timestamp= new ArrayList<String>(0);
	}
	/* (non-Javadoc)
	 * @see com.unibs.ieeesb.pgar.TimeStamp#getDelta(java.util.ArrayList)
	 */
	@Override
	public void getDelta() {
		for(int i=0;i<timestamp.size()-1;i++)
			System.err.println(timestamp.get(i)+"==>"+timestamp.get(i+1)+"="+(Double.parseDouble(timestamp.get(i+1))-Double.parseDouble(timestamp.get(i))));
	}
	/* (non-Javadoc)
	 * @see com.unibs.ieeesb.pgar.TimeStamp#getDelta(java.lang.String)
	 */
	@Override
	public void getDelta(int base) {
		System.err.println("milliseconds unit="+base);
		for(int i=0;i<timestamp.size()-1;i++)
			System.err.println(
					(Double.parseDouble(timestamp.get(i))/base)+
					"==>"+
					(Double.parseDouble(timestamp.get(i))/base)+
					"="+
					(
							((Double.parseDouble(timestamp.get(i+1))/base)-(Double.parseDouble(timestamp.get(i))/base)
									)
							)
					);
	}

	/* (non-Javadoc)
	 * @see com.unibs.ieeesb.pgar.TimeStamp#getTime()
	 */
	@Override
	public void getTime() {
		timestamp.add(""+Instant.now().toEpochMilli());
	}
	/* (non-Javadoc)
	 * @see com.unibs.ieeesb.pgar.TimeStamp#showTime()
	 */
	@Override
	public String showTime() {
		return Instant.now().toString();
	}

}




