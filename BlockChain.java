package MP7.src;

/**
 * Our blockChain class stores nodes that contain Block data, and use node next to link each block to 
 * one another.
 *
 * @author Joyce Gill
 * @author Maria Rodriguez
 */
public class BlockChain {
    
    
/*---------------------------
 * Fields |
 * --------------------------
 */

  //the previous block
  Node first;

  //the next block
  Node last;

  //the cursor block that will be used to iterate through the chain
  Node current;

  //determines the size of the chain
  int size;


/*---------------------------
 * Constructor |
 * --------------------------
 */

  public BlockChain(int initial){
    Block initialBlock = new Block (1, initial, new Hash(new byte[0]));
    this.first = new Node(initialBlock, null);
    current = first;
    this.last = this.first;
    this.size = 1;
  }//BlockChain


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
    return mineBlock;
    //check if its valid
        
  }//BlockMine

  /*
   * getSize()
   * returns the size of the blockchain. Note that number of the blocks provides 
   * a convenient method for quickly determining the size of the chain.
   */
  public int getSize(){
    return size;
  }//getSize

  /*
   * append()
   * adds this block to the list, throwing an IllegalArgumentException if this block 
   * cannot be added to the chain (because it is invalid wrt the rest of the blocks).
   */
  public void append(Block blk) throws IllegalArgumentException{
    //sets the next node to the newly mined block
    last.next = new Node(blk, null);

    //checks if the appended block is valid or not
    if(isValidBlockChain() == false){
      //undo the pointer if invalid
      last.next = null;
      throw new IllegalArgumentException();
    }

    //connects the two 
    last = last.next;

    //points first.next to the last block if there are only 0-1 blocks
    if(size <=1){
      first.next = last;
    }

    this.size++;

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
    last = null;//sets last block to the block before it

    //creates a node that will iterate to the end of the chain, and set last to it
        
    while(current.next != null){
      current = current.next;
    }//while

    last = current;

    size--;
    return true;
  }//removeLast

  /*
   * getHash()
   * returns the hash of the last block in the chain
   */
  public Hash getHash(){
    return last.getData().getHash();
  }//getHash

  /*
   * isValidBlockChain()
   * walks the blockchain and ensures that its blocks 
   * are consistent and valid.
   */
  public boolean isValidBlockChain(){
    //gets the starting balance
    int totalAmt = first.data.getAmount();

    //places current at the start of the chain
    current = first;

    //might have to check each blocks hash and mmake sure theyre in order
    while(current.next != null){
      if(current.data.hash.equals(current.next.data.prevHash)){
        //calculates the transaction
        totalAmt = current.data.getAmount() + current.next.data.getAmount();

        //moves down the chain
        current = current.next;
      } else{
        return false;
      }//if
    }//while

    //check if the transactions are valid
    if(!(totalAmt >=0 || totalAmt <= 300)){
      return false;
    }//if
    return true;
  }//isValidBlockChain

  /*
   * printBalance()
   * prints Alexis’s and Blake’s respective balances
   */
  public void printBalance(){
    //saves the initial amount
    int totalAmt = first.data.amount;
    int alexis = totalAmt/2;
    int blake = totalAmt/2;

    current = first.next;
    while(current.next != null){
      totalAmt = current.data.getAmount();
      
        //check if the amount is negative or positive
      if(totalAmt <= 0){
        alexis += totalAmt; //subtract from alexis
        blake += Math.abs(totalAmt); //add to blake
      } else if(totalAmt >= 0){
        alexis += totalAmt; //subtract from alexis
        blake -= totalAmt; //add to blake
      }//if

      //move through the chain
      current = current.next;
    }//while

    System.out("Alexis: " + alexis + ", Blake: " + blake);
    }//printBalance

  /*
   * toString()
   * returns a string representation of the BlockChain which is simply the
   * string representation of each of its blocks, earliest to latest, one per line.
   */
  public String toString() {
    String str = "";
    current = first;

    while (current != null) {
      str.concat(current.block.toString()).concat("\n");
      current = curent.next;
    }//while
    return str;
  }//toString

/*-----------------------
 * Helper functions|
 * ----------------------
 */
    /*
     * checkAmt()
     * iterates through the chain and makes sure the transactions are valid
     */
    /* 
    public int checkAmt(){
        //saves the initial amount
        int totalAmt = first.data.amount;
        int alexis = totalAmt/2;
        int blake = totalAmt/2;

        current = first.next;
        while(current.next != null){
            //totalAmt = current.data.getAmount() + current.next.data.getAmount();
            if(current.data.getAmount() <= 0)
            current = current.next;
        }
        return totalAmt;
    }//checkAmt
    */

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
