// **********************************************************************
//
// Copyright (c) 2003-2010 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************

// Ice version 3.4.0

package drakkar.oar.slice.login;

// <auto-generated>
//
// Generated from file `Authentication.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>


public abstract class _AuthenticationDisp extends Ice.ObjectImpl implements Authentication
{
    protected void
    ice_copyStateFrom(Ice.Object __obj)
        throws java.lang.CloneNotSupportedException
    {
        throw new java.lang.CloneNotSupportedException();
    }

    public static final String[] __ids =
    {
        "::Ice::Object",
        "::drakkar::oar::slice::action::RCPOperations",
        "::drakkar::oar::slice::action::oneway::Send",
        "::drakkar::oar::slice::action::twoway::Get",
        "::drakkar::oar::slice::login::Authentication"
    };

    public boolean
    ice_isA(String s)
    {
        return java.util.Arrays.binarySearch(__ids, s) >= 0;
    }

    public boolean
    ice_isA(String s, Ice.Current __current)
    {
        return java.util.Arrays.binarySearch(__ids, s) >= 0;
    }

    public String[]
    ice_ids()
    {
        return __ids;
    }

    public String[]
    ice_ids(Ice.Current __current)
    {
        return __ids;
    }

    public String
    ice_id()
    {
        return __ids[4];
    }

    public String
    ice_id(Ice.Current __current)
    {
        return __ids[4];
    }

    public static String
    ice_staticId()
    {
        return __ids[4];
    }

    public final void
    sendAMID_async(drakkar.oar.slice.action.oneway.AMD_Send_sendAMID __cb, byte[] request)
        throws drakkar.oar.slice.error.RequestException
    {
        sendAMID_async(__cb, request, null);
    }

    public final void
    sendSAMI(byte[] request)
        throws drakkar.oar.slice.error.RequestException
    {
        sendSAMI(request, null);
    }

    public final void
    getAMID_async(drakkar.oar.slice.action.twoway.AMD_Get_getAMID __cb, byte[] request)
        throws drakkar.oar.slice.error.RequestException
    {
        getAMID_async(__cb, request, null);
    }

    public final byte[]
    getSAMI(byte[] request)
        throws drakkar.oar.slice.error.RequestException
    {
        return getSAMI(request, null);
    }

    public final drakkar.oar.Seeker
    login(byte[] request)
        throws drakkar.oar.slice.error.RequestException
    {
        return login(request, null);
    }

    public final boolean
    logout(drakkar.oar.Seeker memb)
        throws drakkar.oar.slice.error.RequestException
    {
        return logout(memb, null);
    }

    public static Ice.DispatchStatus
    ___login(Authentication __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        __is.startReadEncaps();
        byte[] request;
        request = Ice.ByteSeqHelper.read(__is);
        __is.endReadEncaps();
        IceInternal.BasicStream __os = __inS.os();
        try
        {
            drakkar.oar.Seeker __ret = __obj.login(request, __current);
            __os.writeSerializable(__ret);
            return Ice.DispatchStatus.DispatchOK;
        }
        catch(drakkar.oar.slice.error.RequestException ex)
        {
            __os.writeUserException(ex);
            return Ice.DispatchStatus.DispatchUserException;
        }
    }

    public static Ice.DispatchStatus
    ___logout(Authentication __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        __is.startReadEncaps();
        drakkar.oar.Seeker memb;
        memb = (drakkar.oar.Seeker)__is.readSerializable();
        __is.endReadEncaps();
        IceInternal.BasicStream __os = __inS.os();
        try
        {
            boolean __ret = __obj.logout(memb, __current);
            __os.writeBool(__ret);
            return Ice.DispatchStatus.DispatchOK;
        }
        catch(drakkar.oar.slice.error.RequestException ex)
        {
            __os.writeUserException(ex);
            return Ice.DispatchStatus.DispatchUserException;
        }
    }

    private final static String[] __all =
    {
        "getAMID",
        "getSAMI",
        "ice_id",
        "ice_ids",
        "ice_isA",
        "ice_ping",
        "login",
        "logout",
        "sendAMID",
        "sendSAMI"
    };

    public Ice.DispatchStatus
    __dispatch(IceInternal.Incoming in, Ice.Current __current)
    {
        int pos = java.util.Arrays.binarySearch(__all, __current.operation);
        if(pos < 0)
        {
            throw new Ice.OperationNotExistException(__current.id, __current.facet, __current.operation);
        }

        switch(pos)
        {
            case 0:
            {
                return drakkar.oar.slice.action.twoway._GetDisp.___getAMID(this, in, __current);
            }
            case 1:
            {
                return drakkar.oar.slice.action.twoway._GetDisp.___getSAMI(this, in, __current);
            }
            case 2:
            {
                return ___ice_id(this, in, __current);
            }
            case 3:
            {
                return ___ice_ids(this, in, __current);
            }
            case 4:
            {
                return ___ice_isA(this, in, __current);
            }
            case 5:
            {
                return ___ice_ping(this, in, __current);
            }
            case 6:
            {
                return ___login(this, in, __current);
            }
            case 7:
            {
                return ___logout(this, in, __current);
            }
            case 8:
            {
                return drakkar.oar.slice.action.oneway._SendDisp.___sendAMID(this, in, __current);
            }
            case 9:
            {
                return drakkar.oar.slice.action.oneway._SendDisp.___sendSAMI(this, in, __current);
            }
        }

        assert(false);
        throw new Ice.OperationNotExistException(__current.id, __current.facet, __current.operation);
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeTypeId(ice_staticId());
        __os.startWriteSlice();
        __os.endWriteSlice();
        super.__write(__os);
    }

    public void
    __read(IceInternal.BasicStream __is, boolean __rid)
    {
        if(__rid)
        {
            __is.readTypeId();
        }
        __is.startReadSlice();
        __is.endReadSlice();
        super.__read(__is, true);
    }

    public void
    __write(Ice.OutputStream __outS)
    {
        Ice.MarshalException ex = new Ice.MarshalException();
        ex.reason = "type drakkar::oar::slice::login::Authentication was not generated with stream support";
        throw ex;
    }

    public void
    __read(Ice.InputStream __inS, boolean __rid)
    {
        Ice.MarshalException ex = new Ice.MarshalException();
        ex.reason = "type drakkar::oar::slice::login::Authentication was not generated with stream support";
        throw ex;
    }
}
