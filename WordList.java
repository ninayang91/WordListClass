public class WordList {

	private String[] a;
	private static final int CAPACITY=6;
	private int capacity;
	private int size=0;
	
	public WordList(){
		a=new String[CAPACITY];
		this.capacity=CAPACITY;
		size=0;
	}

//Store words in order like dictionary
//No duplicates
//All occupied positions in the array appear before any unoccupied position
//use method compareTo(), charAt() from class java.lang.String
//dynamic array expansion: when array is full, and a new insertion is required, double the size
	public WordList(int capacity){
		a=new String[capacity];
		this.capacity=capacity;
		size=0;
	}
	
	public WordList(String[] arrayOfWords){
		int n=arrayOfWords.length;
		capacity=2*n;
		a=new String[capacity];//should be twice the size of the input array arrayOfWords
		//size=0;
		for(int i=0;i<n;i++){//copy arrayOfWords[i] into a[size]
			boolean isDuplicated=false;
			for(int j=0;j<size;j++){
				if(arrayOfWords[i].equals(a[j])){
					isDuplicated=true;
					break;
				}	
			}
			if(!isDuplicated){
				a[size]=arrayOfWords[i];
				size++;
			}
			
		}
		sort();
	}
//Assume all strings in arrayOfWords consists only of lower case letters and no other characters	
	public void insert(String newword){
		for(int i=0;i<size;i++){
			if(a[i].equals(newword)){
				return;
			}
		}
		if(size==capacity)resize();
		a[size]=newword;
		size++;
		sort();
	}
	
	public void sort(){
		for(int i=0;i<size-1;i++){
			for(int j=0;j<size-i-1;j++){
				if(a[j].compareTo(a[j+1])>0) swap(a,j,j+1);
			}
		}
		
	}
	
	public void swap(String[] a, int i,int j){
		String temp=a[i];
		a[i]=a[j];
		a[j]=temp;
	}
	
	public WordList sublist(char init, char fin){
		String[] temp=new String[size];
		int bsize=0;
		
		for(int i=0;i<size;i++){
			//int value=(int)Integer.parseInt(Character.toString(a[i].charAt(0)));
			if(a[i].charAt(0)>=init && a[i].charAt(0)<=fin){
				temp[bsize]=a[i];
				bsize++;
			}
		}
		String[] b=new String[bsize];

		for(int j=0;j<bsize;j++){//We have to make an array with no null at any position
			b[j]=temp[j];
		}
		return new WordList(b);//can not be new WordList(temp)
		
	}
	
	public void remove(String word){
		int index=find(word);
		if(index<0) return;
		for(int i=index;i<size-1;i++){
			a[i]=a[i+1];
		}
		a[size]=null;
		size--;
		
	}
	
	public int find(String word){
		return find(word, 0,size-1);
	}
	
	public int find(String word, int low, int high){
	
		while(low<=high){
			int mid=(low+high)/2;
			if(a[mid].compareTo(word)>0){
				return find(word,low,mid-1);
			}else if(a[mid].compareTo(word)<0){
				return find(word,mid+1,high);
			}else{
				return mid;
			}
		}
		return -1;
	}
	
	public void resize(){
		capacity *= 2;
		String[] temp=new String[capacity];
		for(int i=0;i<size;i++){
			temp[i]=a[i];
		}
		a=temp;
	}
	
	public String getWordAt(int i){// throws ArrayIndexOutOfBoundsException{
		if(i<size && i>=0){
			return a[i];
		}else{
			throw new ArrayIndexOutOfBoundsException("This is not a valid index");
			//System.out.println("This is not a valid index");
			//return null;
		}
	}
	
	public int getSize(){
		return size;
	}
	
	public int getCapacity(){
		return capacity;
	}
	
	public String toString() {
        String longString = "";
	if(size==0)
	    longString = "The list is empty";
	else
	    longString = a[0];
        for (int i = 1; i < size; i++) {
            longString =longString + "\n" + a[i];
        }
        return longString;
    }
}
