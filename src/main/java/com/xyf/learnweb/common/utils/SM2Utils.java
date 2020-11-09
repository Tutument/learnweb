/** create by gwei at 2017-10-28 上午9:45:10 */
package com.xyf.learnweb.common.utils;

import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECCurve.Config;
import org.bouncycastle.math.ec.ECMultiplier;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.MixedNafR2LMultiplier;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Arrays;

public class SM2Utils {

	private static ECCurve defaultCurve = createDefaultCurve();
	private static ECPoint defaultG = defaultCurve.createPoint(SM2Const.Gx, SM2Const.Gy);
	private static ECCurve defaultAddCurve = createDefaultAddCurve();

	private static ECCurve createDefaultCurve() {
		ECCurve curve = new ECCurve.Fp(SM2Const.p, SM2Const.a, SM2Const.b);
		ECMultiplier multiplier = new MixedNafR2LMultiplier();
		Config config = curve.configure();
		config.setMultiplier(multiplier);
		curve = config.create();
		return curve;
	}

	private static ECCurve createDefaultAddCurve() {
		return configureCurve(defaultCurve, ECCurve.COORD_JACOBIAN);
	}

	public static ECCurve getDefaultCurve() {
		return defaultCurve;
	}

	public static ECPoint getDefaultG() {
		return defaultG;
	}

	//if x <= a <= y return true, otherwise return false;
	public static boolean isEqualBetween(BigInteger a, BigInteger x, BigInteger y) {
		if (a.compareTo(x) == -1) {
			return false;
		}
		if (a.compareTo(y) == 1) {
			return false;
		}
		return true;
	}

	public static byte[] concate(byte[] ZA, byte[] M) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		if (ZA != null && ZA.length > 0) {
			try {
				baos.write(ZA);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		if (M != null && M.length > 0) {
			try {
				baos.write(M);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return baos.toByteArray();
	}

	public static BigInteger addModn(BigInteger a, BigInteger b, BigInteger n) {
		return a.add(b).mod(n);
	}

	public static ECPoint addMulitiPoint(ECCurve curve, BigInteger s, ECPoint G, BigInteger t, ECPoint PA) {
		ECMultiplier mulitiplier = curve.getMultiplier();
		if (!(mulitiplier instanceof MixedNafR2LMultiplier)) {
			mulitiplier = new MixedNafR2LMultiplier();
			curve = curve.configure().setMultiplier(mulitiplier).create();
		}

		ECPoint sG = curve.getMultiplier().multiply(G, s);

		//ByteArrayPrinter.printByteArray(sG.getXCoord().getEncoded(), "x0", 64);
		//ByteArrayPrinter.printByteArray(sG.getYCoord().getEncoded(), "y0", 64);

		ECPoint tPA = curve.getMultiplier().multiply(PA, t);

		//ByteArrayPrinter.printByteArray(tPA.getXCoord().getEncoded(), "x00", 64);
		//ByteArrayPrinter.printByteArray(tPA.getYCoord().getEncoded(), "y00", 64);

		return addPoint(curve, sG, tPA);
	}

	private static ECPoint addPoint(ECCurve curve, ECPoint x, ECPoint y) {
		ECCurve curveOrig = curve;

		ECCurve curveAdd = defaultAddCurve;

		ECPoint Ra = curveAdd.getInfinity();
		ECPoint Td = curveAdd.importPoint(x);
		Ra = Ra.add(Td);

		ECPoint Tj = curveAdd.importPoint(y);

		Ra = Ra.add(Tj);

		//System.out.println("curveOrig:" + curveOrig.getCoordinateSystem());
		//System.out.println("curveAdd:" + curveAdd.getCoordinateSystem());
		return curveOrig.importPoint(Ra);
	}

	private static ECCurve configureCurve(ECCurve c, int coord) {
		if (c.getCoordinateSystem() == coord) {
			return c;
		}

		if (!c.supportsCoordinateSystem(coord)) {
			throw new IllegalArgumentException("Coordinate system " + coord + " not supported by this curve");
		}

		return c.configure().setCoordinateSystem(coord).create();
	}

	//generate x and return, where a <= x <= b
	public static BigInteger getRandomBetween(BigInteger a, BigInteger b) {
		SecureRandom sRandom = new SecureRandom();
		byte[] k = new byte[b.toByteArray().length];
		BigInteger bint = new BigInteger(1, k);
		while (!isEqualBetween(bint, a, b)) {
			sRandom.nextBytes(k);
			bint = new BigInteger(1, k);
		}
		return bint;
	}

	public static byte[] getZA(byte[] idcarrier, byte[] PAxA, byte[] PAyA) throws Exception {
		String len = Integer.toHexString(idcarrier.length * 8);
		while (len.length() < 4) {
			len = "0" + len;
		}
		byte[] entla = ByteArrayUtil.hexStringToBytes(len);
		byte[] a = Arrays.copyOfRange(SM2Const.a.toByteArray(), 1, SM2Const.a.toByteArray().length);
		byte[] b = SM2Const.b.toByteArray();
		byte[] xG = SM2Const.Gx.toByteArray();
		byte[] yG = Arrays.copyOfRange(SM2Const.Gy.toByteArray(), 1, SM2Const.Gy.toByteArray().length);
		ByteBuffer bb = ByteBuffer.allocate(entla.length + idcarrier.length + a.length + b.length + xG.length
				+ yG.length + PAxA.length + PAyA.length);
		bb.put(entla, 0, entla.length);
		bb.put(idcarrier, 0, idcarrier.length);
		bb.put(a, 0, a.length);
		bb.put(b, 0, b.length);
		bb.put(xG, 0, xG.length);
		bb.put(yG, 0, yG.length);
		bb.put(PAxA, 0, PAxA.length);
		bb.put(PAyA, 0, PAyA.length);
		//		System.out.println("ZABRH : " + ByteArrayUtil.bytesToHexString(bb.array()));
		byte[] ZA = SM3Helper.hash(bb.array());
		return ZA;
	}

}
