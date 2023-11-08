package MP7.src;

public class BlockChain {
    
    
/*---------------------------
 * Fields |
 * --------------------------
 */

    //the previous block
    Node first;

    //the next block
    Node last;

    //determines the size of the chain
    int size;


/*---------------------------
 * Constructor |
 * --------------------------
 */

    public BlockChain(int initial){
        Block initialBlock = new Block (1, initial, new Hash(new byte[0]));
        this.first = new Node(initialBlock, null);
        this.last = this.first;
        this.size = 1;
    }


 /*--------------------------
 * Methods |
 * --------------------------
 */

    /*
     * BlockMine()
     * mines a new candidate block to be added to the end of the chain. 
     * The returned Block should be valid to add onto this chain.
     */
    public Block mine(int amount){
        //mine a block
        Block mineBlock = new Block(this.getSize(), amount, this.last.data.getHash());

        /* 
        if(mineBlock.getHash().isValid()){
            return mineBlock;
        }
        */
        return mineBlock;
        //check if its valid

        
    }//BlockMine

    /*
     * getSize()
     * returns the size of the blockchain. Note that number of the blocks provides 
     * a convenient method for quickly determining the size of the chain.
     */
    public int getSize(){
        return this.size;
    }//getSize

    /*
     * append()
     * adds this block to the list, throwing an IllegalArgumentException if this block 
     * cannot be added to the chain (because it is invalid wrt the rest of the blocks).
     */
    public void append(Block blk){
        //if adding the first block
        if(size == 1){
            //sets the node
            this.first.next = new Node(blk, null);
            
            this.last = this.first.next; //links the two
            //this.last.prev = this.first;

            //sets the node's data = the block
            this.last.data = blk;

        } else{
            //sets the last item as the new block
            this.last.next = new Node(blk, null);

            //connects the two 
            //this.last.prev = this.last;
            //this.last = this.last.next;

            //sets the node's data = the block
            this.last.next.data = blk;
        }
        //add block to list


        this.size++;

        //throw exception if it is invalid

    }//append

    /*
     * removeLast()
     * removes the last block from the chain, returning true. If the chain only contains 
     * a single block, then removeLast does nothing and returns false.
     */
    public boolean removeLast(){
        //if the chain is empty or has 1 block
        if(this.size <= 1){
            return false;
        }//if
        
        //remove block
        this.last.next = null;//sets last block to the block before it

        //somehow set this.first = last.prev;

        this.size--;
        return true;
    }//removeLast

    /*
     * getHash()
     * returns the hash of the last block in the chain
     */
    public Hash getHash(){
        return this.last.getData().getHash();
    }//getHash

    /*
     * isValidBlockChain()
     * walks the blockchain and ensures that its blocks 
     * are consistent and valid.
     */
    public boolean isValidBlockChain(){
        //might have to check each blocks hash and mmake sure theyre in order
        return false;
    }//isValidBlockChain

    /*
     * printBalance()
     * prints Alexis’s and Blake’s respective balances
     */
    public void printBalance(){
        //return this.last.amount;
    }//printBalance

    /*
     * toString()
     * returns a string representation of the BlockChain which is simply the
     * string representation of each of its blocks, earliest to latest, one per line.
     */
    public String toString(){
        return "";
    }//toString

/*-----------------------
 * Inner class|
 * ----------------------
 */
/**
   * Nodes in the linked list.
   */
  class Node
  {
    // +--------+-----------------------------------------------------------
    // | Fields |
    // +--------+

    /**
     * The data stored in the node.
     */
    Block data;

    /**
     * The next node in the list.  Set to null at the end of the list.
     */
    Node next;

    Node prev;

    // +--------------+-----------------------------------------------------
    // | Constructors |
    // +--------------+

    /**
     * Create a new node with specified data and next.
     */
    public Node(Block data, Node next)
    {
      this.data = data;
      this.next = next;
    } // Node(T, Node)

    /**
     * 
     */
    public Block getData(){
        return this.data;
      } // getData()
  
    /**
     * 
     */
    public Node getNext(){
        return this.next;
    } // getData()


  } // class Node
}
