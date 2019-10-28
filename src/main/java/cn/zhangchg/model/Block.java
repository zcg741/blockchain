package cn.zhangchg.model;

import cn.zhangchg.utils.StringUtils;

import java.util.ArrayList;

public class Block {

    public String hash;

    public String previousHash;
    /**
     * our data will be a simple message.
     */
    private String data;
    /**
     * as number of milliseconds since 1/1/1970.
     */
    private long timeStamp;
    private int nonce;

    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = System.currentTimeMillis();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        return StringUtils.applySha256(
                previousHash +
                        timeStamp +
                        nonce +
                        data
        );
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0"
        while(!hash.substring( 0, difficulty).equals(target)) {
            nonce ++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }

    public static Boolean isChainValid(ArrayList<Block> blocks, int difficulty) {
        Block currentBlock;
        Block previousBlock;

        String hashTarget = new String(new char[difficulty]).replace('\0', '0');
        //loop through blocks to check hashes:
        for (int i = 1; i < blocks.size(); i++) {
            currentBlock = blocks.get(i);
            previousBlock = blocks.get(i - 1);
            //compare registered hash and calculated hash:
            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Current Hashes not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
            //check if hash is solved
            if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }

}

