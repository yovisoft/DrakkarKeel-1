// **********************************************************************
//
// Copyright (c) 2003-2010 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************

// Ice version 3.4.0

package drakkar.oar.slice.action.oneway;

// <auto-generated>
//
// Generated from file `Oneway.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>


public interface _SendOperationsNC
{
    void sendSAMI(byte[] request)
        throws drakkar.oar.slice.error.RequestException;

    void sendAMID_async(AMD_Send_sendAMID __cb, byte[] request)
        throws drakkar.oar.slice.error.RequestException;
}
