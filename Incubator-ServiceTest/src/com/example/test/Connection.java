package com.example.test;

/*******************************************************************************
 * Copyright (c) 2013 ''Azgabast''.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     ''Azgabast'' - initial API and implementation
 ******************************************************************************/
import java.net.InetAddress;

/**
 * @author ''Azgabast''
 * 
 */
public class Connection 
//implements Parcelable
{
	/**
	 * The time passed since this connection was created.
	 */
	long lifeSpan;
	/**
	 * The Ip of the device to connect with.
	 */
	InetAddress ip;

	/**
	 * The id of the device to connect with.
	 */
	String id;

	/**
	 * The port used for the connection.
	 */
	int port;

	/**
	 * 
	 * The list of possible states of a connection.
	 * 
	 */
	enum State
	{
		/**
		 * State on.
		 */
		On, /**
		 * State off.
		 */
		Off, /**
		 * State timedout.
		 */
		Timedout
	}

	/**
	 * The state of the connection.
	 */
	State s;

	/**
	 * @param ip
	 *            The device ip.
	 * @param id
	 *            The device id.
	 * @param port
	 *            The port for the connection
	 */
	public Connection(InetAddress ip, String id, int port)
	{
		this.ip = ip;
		this.id = id;
		this.s = State.Off;
		this.port = port;
	}

	/**
	 * @param in
	 *            The received parcel.
	 * @throws UnknownHostException
	 *             This is caused by the ip.
	 */
/*	public Connection(Parcel in) throws UnknownHostException
	{
		String[] data = new String[3];
		in.readStringArray(data);
		this.ip = InetAddress.getByName(data[0]);
		this.id = data[1];
		this.port = Integer.parseInt(data[2]);
	}
*/
	/**
	 * @param s
	 *            The state to be changed with.
	 * 
	 */
	public void setState(State s)
	{
		this.s = s;
		if (this.s == State.On)
			lifeSpan = System.currentTimeMillis();
	}

	/**
	 * @return The ip of the connection.
	 */
	public InetAddress getIp()
	{
		return this.ip;
	}

	/**
	 * @return The id of the device connected with.
	 */
	public String getId()
	{
		return this.id;
	}

	/**
	 * @return The state of the connection.
	 */
	public State getState()
	{
		return this.s;
	}

	/**
	 * @return The port for the current connection.
	 */
	public int getPort()
	{
		return port;
	}

	/**
	 * @param port
	 *            The port for the current connection.
	 */
	public void setPort(int port)
	{
		this.port = port;
	}

	/**
	 * @return The time this connection was on
	 */
	public long getConnectionTime()
	{
		return System.currentTimeMillis() - lifeSpan;
	}

/*	@Override
	public int describeContents()
	{
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, @SuppressWarnings("unused") int flags)
	{

		dest.writeStringArray(new String[] { this.ip.getHostAddress(), this.id,
				String.valueOf(this.port)

		});

	}
*/
	/**
	 * The Parcel generator.
	 */
/*	public static final Parcelable.Creator<Connection> CREATOR = new Parcelable.Creator<Connection>()
	{

		@Override
		public Connection createFromParcel(Parcel in)
		{

			try
			{
				return new Connection(in);
			}
			catch (UnknownHostException e)
			{
				e.printStackTrace();
			}
			return null;

		}

		@Override
		public Connection[] newArray(int size)
		{

			return new Connection[size];

		}

	};
  */
}
