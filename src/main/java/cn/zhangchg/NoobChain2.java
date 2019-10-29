package cn.zhangchg;

import cn.zhangchg.model.Block;
import cn.zhangchg.model.Transaction;
import cn.zhangchg.model.Wallet;
import cn.zhangchg.utils.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.google.gson.GsonBuilder;

import java.security.Security;
import java.util.ArrayList;

public class NoobChain2 {
    public static int difficulty = 6;
    public static ArrayList<Block> blocks = new ArrayList<Block>();
    public static Wallet walletA;
    public static Wallet walletB;

    public static void main(String[] args) {
        //Setup Bouncey castle as a Security Provider
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        //Create the new wallets
        walletA = new Wallet();
        walletB = new Wallet();
        //Test public and private keys
        System.out.println("Private and public keys:");
        System.out.println(StringUtils.getStringFromKey(walletA.privateKey));
        System.out.println(StringUtils.getStringFromKey(walletA.publicKey));
        //Create a test transaction from WalletA to walletB
        Transaction transaction = new Transaction(walletA.publicKey, walletB.publicKey, 5, null);
        transaction.generateSignature(walletA.privateKey);
        //Verify the signature works and verify it from the public key
        System.out.println("Is signature verified");
        System.out.println(transaction.verifiySignature());

    }
}
