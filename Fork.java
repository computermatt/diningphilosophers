public class Fork implements IFork 
{

	private boolean allocated = false;
	
	public void acquire() 
	{
		synchronized(this) 
		{
			while (this.allocated==true)
			{
				try 
				{
					wait();
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
		}
		allocated = true;
	}

	public void release() 
	{
		synchronized(this)
		{
			allocated = false;
			notifyAll();
		}
	}
	public boolean isAllocated()
	{
    	return allocated;
    }
}
	