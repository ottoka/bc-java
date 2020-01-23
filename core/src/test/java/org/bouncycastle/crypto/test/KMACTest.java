package org.bouncycastle.crypto.test;

import org.bouncycastle.crypto.macs.KMAC;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;
import org.bouncycastle.util.test.SimpleTest;

/**
 * KMAC test vectors from:
 * <p>
 * https://csrc.nist.gov/CSRC/media/Projects/Cryptographic-Standards-and-Guidelines/documents/examples/KMAC_samples.pdf
 */
public class KMACTest
    extends SimpleTest
{
    public String getName()
    {
        return "KMAC";
    }

    public void performTest()
        throws Exception
    {
        KMAC kmac = new KMAC(128, Strings.toByteArray(""));

        isEquals("KMACwithSHAKE128", kmac.getAlgorithmName());

        kmac.init(new KeyParameter(Hex.decode(
            "404142434445464748494A4B4C4D4E4F505152535455565758595A5B5C5D5E5F")));

        kmac.update(Hex.decode("00010203"), 0, 4);

        byte[] res = new byte[32];

        kmac.doFinal(res, 0, res.length);

        isTrue("oops: " + Hex.toHexString(res), Arrays.areEqual(Hex.decode("E5780B0D3EA6F7D3A429C5706AA43A00FADBD7D49628839E3187243F456EE14E"), res));

        kmac = new KMAC(128, Strings.toByteArray("My Tagged Application"));

        kmac.init(new KeyParameter(Hex.decode(
            "404142434445464748494A4B4C4D4E4F505152535455565758595A5B5C5D5E5F")));

        kmac.update(Hex.decode("00010203"), 0, 4);

        res = new byte[32];

        kmac.doFinal(res, 0, res.length);

        isTrue("oops: " + Hex.toHexString(res), Arrays.areEqual(Hex.decode("3B1FBA963CD8B0B59E8C1A6D71888B7143651AF8BA0A7070C0979E2811324AA5"), res));

        kmac = new KMAC(128, Strings.toByteArray("My Tagged Application"));

        kmac.init(new KeyParameter(Hex.decode(
            "404142434445464748494A4B4C4D4E4F505152535455565758595A5B5C5D5E5F")));

        byte[] data = Hex.decode(
            "000102030405060708090A0B0C0D0E0F101112131415161718191A1B1C1D1E1" +
                "F202122232425262728292A2B2C2D2E2F303132333435363738393A3B3C3D3" +
                "E3F404142434445464748494A4B4C4D4E4F505152535455565758595A5B5C5" +
                "D5E5F606162636465666768696A6B6C6D6E6F707172737475767778797A7B7" +
                "C7D7E7F808182838485868788898A8B8C8D8E8F909192939495969798999A9" +
                "B9C9D9E9FA0A1A2A3A4A5A6A7A8A9AAABACADAEAFB0B1B2B3B4B5B6B7B8B9B" +
                "ABBBCBDBEBFC0C1C2C3C4C5C6C7");
        kmac.update(data, 0, data.length);

        res = new byte[32];

        kmac.doFinal(res, 0, res.length);

        isTrue("oops:" + Hex.toHexString(res), Arrays.areEqual(Hex.decode("1F5B4E6CCA02209E0DCB5CA635B89A15E271ECC760071DFD805FAA38F9729230"), res));

        kmac = new KMAC(256, Strings.toByteArray("My Tagged Application"));

        isEquals("KMACwithSHAKE256", kmac.getAlgorithmName());

        kmac.init(new KeyParameter(Hex.decode("404142434445464748494A4B4C4D4E4F505152535455565758595A5B5C5D5E5F")));

        data = Hex.decode("00 01 02 03");
        kmac.update(data, 0, data.length);

        res = new byte[64];

        kmac.doFinal(res, 0, res.length);

        isTrue("oops:" + Hex.toHexString(res), Arrays.areEqual(Hex.decode("20C570C31346F703C9AC36C61C03CB64C3970D0CFC787E9B79599D273A68D2F7F69D4CC3DE9D104A351689F27CF6F5951F0103F33F4F24871024D9C27773A8DD"), res));

        kmac = new KMAC(256, Strings.toByteArray(""));

        kmac.init(new KeyParameter(Hex.decode(
            "404142434445464748494A4B4C4D4E4F505152535455565758595A5B5C5D5E5F")));

        data = Hex.decode(
            "000102030405060708090A0B0C0D0E0F101112131415161718191A1B1C1D1E1" +
                "F202122232425262728292A2B2C2D2E2F303132333435363738393A3B3C3D3" +
                "E3F404142434445464748494A4B4C4D4E4F505152535455565758595A5B5C5" +
                "D5E5F606162636465666768696A6B6C6D6E6F707172737475767778797A7B7" +
                "C7D7E7F808182838485868788898A8B8C8D8E8F909192939495969798999A9" +
                "B9C9D9E9FA0A1A2A3A4A5A6A7A8A9AAABACADAEAFB0B1B2B3B4B5B6B7B8B9B" +
                "ABBBCBDBEBFC0C1C2C3C4C5C6C7");
        kmac.update(data, 0, data.length);

        res = new byte[64];

        kmac.doFinal(res, 0, res.length);

        isTrue("oops:" + Hex.toHexString(res), Arrays.areEqual(Hex.decode("75358CF39E41494E949707927CEE0AF20A3FF553904C86B08F21CC414BCFD691589D27CF5E15369CBBFF8B9A4C2EB17800855D0235FF635DA82533EC6B759B69"), res));

        kmac = new KMAC(256, Strings.toByteArray("My Tagged Application"));

        kmac.init(new KeyParameter(Hex.decode(
            "404142434445464748494A4B4C4D4E4F505152535455565758595A5B5C5D5E5F")));

        data = Hex.decode(
            "000102030405060708090A0B0C0D0E0F101112131415161718191A1B1C1D1E1" +
                "F202122232425262728292A2B2C2D2E2F303132333435363738393A3B3C3D3" +
                "E3F404142434445464748494A4B4C4D4E4F505152535455565758595A5B5C5" +
                "D5E5F606162636465666768696A6B6C6D6E6F707172737475767778797A7B7" +
                "C7D7E7F808182838485868788898A8B8C8D8E8F909192939495969798999A9" +
                "B9C9D9E9FA0A1A2A3A4A5A6A7A8A9AAABACADAEAFB0B1B2B3B4B5B6B7B8B9B" +
                "ABBBCBDBEBFC0C1C2C3C4C5C6C7");
        kmac.update(data, 0, data.length);

        res = new byte[64];

        kmac.doFinal(res, 0, res.length);

        isTrue("oops:" + Hex.toHexString(res), Arrays.areEqual(Hex.decode("B58618F71F92E1D56C1B8C55DDD7CD188B97B4CA4D99831EB2699A837DA2E4D970FBACFDE50033AEA585F1A2708510C32D07880801BD182898FE476876FC8965"), res));

        doFinalTest();
        longBlockTest();

        checkKMAC(128, new KMAC(128, new byte[0]), Hex.decode("eeaabeef"));
        checkKMAC(256, new KMAC(256, null), Hex.decode("eeaabeef"));
        checkKMAC(128, new KMAC(128, new byte[0]), Hex.decode("eeaabeef"));
        checkKMAC(128, new KMAC(128, null), Hex.decode("eeaabeef"));
        checkKMAC(256, new KMAC(256,  null), Hex.decode("eeaabeef"));
    }

    private void doFinalTest()
    {
        KMAC kmac = new KMAC(128, Strings.toByteArray("My Tagged Application"));

        kmac.init(new KeyParameter(Hex.decode(
            "404142434445464748494A4B4C4D4E4F505152535455565758595A5B5C5D5E5F")));
        
        kmac.update(Hex.decode("00010203"), 0, 4);

        byte[] res = new byte[32];

        kmac.doOutput(res, 0, res.length);

        isTrue(Hex.toHexString(res), Arrays.areEqual(Hex.decode("31a44527b4ed9f5c6101d11de6d26f0620aa5c341def41299657fe9df1a3b16c"), res));

        kmac.doOutput(res, 0, res.length);

        isTrue(!Arrays.areEqual(Hex.decode("31a44527b4ed9f5c6101d11de6d26f0620aa5c341def41299657fe9df1a3b16c"), res));

        kmac.doFinal(res, 0, res.length);

        kmac.update(Hex.decode("00010203"), 0, 4);

        kmac.doFinal(res, 0, res.length);

        isTrue(Arrays.areEqual(Hex.decode("3B1FBA963CD8B0B59E8C1A6D71888B7143651AF8BA0A7070C0979E2811324AA5"), res));

        kmac.update(Hex.decode("00010203"), 0, 4);

        kmac.doOutput(res, 0, res.length);

        isTrue(Arrays.areEqual(Hex.decode("31a44527b4ed9f5c6101d11de6d26f0620aa5c341def41299657fe9df1a3b16c"), res));

        kmac.doFinal(res, 0, res.length);

        isTrue(Hex.toHexString(res), Arrays.areEqual(Hex.decode("ffcb48c7620ccd67d1c83224186892cef2f2a99278d5cfdde10e48bdc89718c2"), res));
    }

    private void longBlockTest()
    {
        byte[] data = new byte[16000];
        byte[] res = new byte[64];

        for (int i = 0; i != data.length; i++)
        {
            data[i] = (byte)i;
        }

        for (int i = 10000; i != data.length; i++)
        {
            KMAC kmac = new KMAC(128, Arrays.copyOfRange(data, 0, i));

            kmac.update(Hex.decode("00010203"), 0, 4);

            kmac.doFinal(res, 0);
        }

        KMAC kmac = new KMAC(256, new byte[200]);

        kmac.update(Arrays.copyOfRange(data, 0, 200), 0, 200);

        kmac.doFinal(res, 0);

        isTrue(Hex.toHexString(res), Arrays.areEqual(Hex.decode("a31a638ab570d6307b33f4e8f14b3e3e3d1ea30629fb9f593ef27eac9d2576676855aee121ac468086b059d87bd480bee99558ca320c47a70ca5bc6a190aeb5a"), res));
    }

    private void checkKMAC(int bitSize, KMAC kmac, byte[] msg)
    {
        KMAC ref = new KMAC(bitSize, null);

        ref.update(msg, 0, msg.length);
        kmac.update(msg, 0, msg.length);

        byte[] res1 = new byte[32];
        byte[] res2 = new byte[32];

        ref.doFinal(res1, 0, res1.length);
        kmac.doFinal(res2, 0, res2.length);

        isTrue(Arrays.areEqual(res1, res2));
    }

    public static void main(
        String[] args)
    {
        runTest(new KMACTest());
    }
}