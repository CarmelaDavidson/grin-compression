package edu.grinnell.csc207.compression;

import java.util.ArrayList;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * A HuffmanTree derives a space-efficient coding of a collection of byte
 * values.
 *
 * The huffman tree encodes values in the range 0--255 which would normally
 * take 8 bits. However, we also need to encode a special EOF character to
 * denote the end of a .grin file. Thus, we need 9 bits to store each
 * byte value. This is fine for file writing (modulo the need to write in
 * byte chunks to the file), but Java does not have a 9-bit data type.
 * Instead, we use the next larger primitive integral type, short, to store
 * our byte values.
 */
public class HuffmanTree {
    public Node root;

    public class Node implements Comparable<Node>{
        public Short rep;
        public Integer freq;
        public Boolean interior;
        public Node l;
        public Node r;

        public Node(Short rep, Integer freq) {
            this.rep = rep;
            this.freq = freq;
            this.interior = false;
        }
        public Node(Integer freq, Node l, Node r){
            this.freq = freq;
            this.interior = true;
            this.l = l;
            this.r = r;
        }
        @Override
        public int compareTo(Node n){
            if(n.freq == null){
                return 1;
            }
            if(this.freq == null){
                return -1;
            }
            return n.freq - this.freq;
        }

    }

    /**
     * Constructs a new HuffmanTree from a frequency map.
     * @param freqs a map from 9-bit values to frequencies.
     */
    public HuffmanTree (Map<Short, Integer> freqs) {
        PriorityQueue<Node> que = new PriorityQueue<Node>();
        freqs.put(Short.parseShort("256"), 1);
        Short[] keys = new Short[freqs.size()];
        freqs.keySet().toArray(keys);
        for(int i = 0; i < keys.length; i++){
            Node n = new Node(keys[i], freqs.get(keys[i]));
            que.add(n);
        }
        while(que.size() > 1){
            Node l = que.poll();
            Node r = que.poll();
            Node temp = new Node((l.freq + r.freq), l, r);
            que.add(temp);
        }
        this.root = que.poll();
    }

    /**
     * Constructs a new HuffmanTree from the given file.
     * 
     * @param in the input file (as a BitInputStream)
     */
    public HuffmanTree(BitInputStream in) {
        for()
    }

    public String preorder(Node n){
        if(n.interior = true){
            return "1";
        }
        String out = "0";
        out = out + Integer.toBinaryString(n.rep);
        out = out + preorder(n.l);
        out = out + preorder(n.r);
        return out;
    }

    /**
     * Writes this HuffmanTree to the given file as a stream of bits in a
     * serialized format.
     * 
     * @param out the output file as a BitOutputStream
     */
    public void serialize(BitOutputStream out) {
        String str = preorder(root);
    }

    /**
     * Encodes the file given as a stream of bits into a compressed format
     * using this Huffman tree. The encoded values are written, bit-by-bit
     * to the given BitOuputStream.
     * 
     * @param in  the file to compress.
     * @param out the file to write the compressed output to.
     */
    public void encode(BitInputStream in, BitOutputStream out) {
        
    }

    /**
     * Decodes a stream of huffman codes from a file given as a stream of
     * bits into their uncompressed form, saving the results to the given
     * output stream. Note that the EOF character is not written to out
     * because it is not a valid 8-bit chunk (it is 9 bits).
     * 
     * @param in  the file to decompress.
     * @param out the file to write the decompressed output to.
     */
    public void decode(BitInputStream in, BitOutputStream out) {
        // TODO: fill me in!
    }
}
