package cn.hankchan.stu.netcdf;

import java.io.IOException;

import ucar.ma2.ArrayDouble;
import ucar.ma2.Index;
import ucar.ma2.InvalidRangeException;
import ucar.nc2.Dimension;
import ucar.nc2.NetcdfFileWriteable;

@SuppressWarnings("deprecation")
public class WriteDataToNetCdf {
	public static void main(String[] args) throws IOException {
		NetcdfFileWriteable ncfile = NetcdfFileWriteable.openExisting("D:\\work\\netcdf\\testWrite.nc", true);
		Dimension latDim = ncfile.getDimensions().get(0);
		Dimension lonDim = ncfile.getDimensions().get(1);
		ArrayDouble A = new ArrayDouble.D2(latDim.getLength(), lonDim.getLength());
		int i, j;
		Index ima = A.getIndex();
		for (i = 0; i < latDim.getLength(); i++) {
			for (j = 0; j < lonDim.getLength(); j++) {
				A.setDouble(ima.set(i, j), (double) (2));
			}
		}
		int[] origin = new int[2];
		try {
			ncfile.write("temperature", origin, A);
			ncfile.close();
		} catch (IOException e) {
			System.err.println("ERROR writing file");
		} catch (InvalidRangeException e) {
			e.printStackTrace();
		}
	}
}
