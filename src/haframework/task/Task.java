/**
 * 
 */
package haframework.task;

import haframework.events.TouchEvent;
import haframework.task.conf.eTaskState;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Vector;

/**
 * @author hjb
 *
 */
public class Task
{
	//-------------------------------------------- private member ----------------------------------------------------
	
	protected int m_priority = 0;
	protected int m_status = 0;
	
	private ArrayList<DelayCallData> m_delayCallList = null;
	
	//-------------------------------------------- public function ---------------------------------------------------
	
	/**
	 * @desc	Task class
	 */
	public Task()
	{
		m_delayCallList = new ArrayList<DelayCallData>();
		
		m_status = eTaskState.STATUS_IDLE;
		m_priority = 0;
		
		TaskManager.Singleton().insertTask( this );
	}
	
	/**
	 * @desc	start this task
	 * @param	priority
	 * @return
	 */
	public boolean Start( int priority )
	{
		return TaskManager.Singleton().Add( this, priority );
	}

	/**
	 * @desc	stop this task
	 * @return
	 */
	public boolean Stop()
	{
		return TaskManager.Singleton().Remove( this );
	}
	
	/**
	 * @desc	add delay call
	 * @param	func
	 * @param	time
	 */
	public boolean AddDelayCall( String func, int time )
	{
		Method funcCall;
		
		try
		{
			funcCall = this.getClass().getMethod( func );
		}
		catch( Exception e )
		{
			System.out.println( "[Error] the method is not exist" );
			return false;
		}
		
		DelayCallData delayCallData = new DelayCallData();
		delayCallData._delayCall = funcCall;
		delayCallData._delayTime = time;
		
		m_delayCallList.add( delayCallData );
		
		return true;
	}
	
	/**
	 * @desc	return the task status
	 * @return
	 */
	public int GetStatus()
	{
		return this.m_status;
	}
	
	/**
	 * @desc	set the status of this task
	 * @param	status
	 */
	public void SetStatus( int status )
	{
		this.m_status = status;
	}
	
	/**
	 * @desc	return the priority of this task
	 * @return
	 */
	public int GetPriority()
	{
		return this.m_priority;
	}
	
	/**
	 * @desc	set this task's priority
	 * @param 	priority
	 */
	public void SetPriority( int priority )
	{
		this.m_priority = priority;
	}
	
	/**
	 * @desc	return the delay call list
	 * @return
	 */
	public ArrayList<DelayCallData> GetDelayCall()
	{
		return m_delayCallList;
	}
	
	public void vBegin(){}
	public void vEnd(){}
	public void vDestroy(){}
	public void vMain( float elapsed ){}
	public void vDraw( float elapsed ){}
	public boolean vOnTouchEvent( Vector<TouchEvent> events ){ return false; }

}

/**
 * @desc	struct of delay call
 * @author	hjb
 *
 */
class DelayCallData
{
	public Method	_delayCall;
	public float	_delayTime;
}
