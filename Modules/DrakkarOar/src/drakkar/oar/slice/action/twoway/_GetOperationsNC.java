// **********************************************************************
//
// Copyright (c) 2003-2010 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************

// Ice version 3.4.0

package drakkar.oar.slice.action.twoway;

// <auto-generated>
//
// Generated from file `Twoway.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>


public interface _GetOperationsNC
{
    byte[] getSAMI(byte[] request)
        throws drakkar.oar.slice.error.RequestException;

    void getAMID_async(AMD_Get_getAMID __cb, byte[] request)
        throws drakkar.oar.slice.error.RequestException;
}
