# NSWI145 - HW No. 4 - Intermediary 

### Summary

1. Intermediary intercepts the message and verifies that the header contains `policy` element. If policy not present default security policy is selected and header removed.
2. When the header is processed and intermediary is about to send the request to the service the `timestamp` is recorded.
3. On response arrival the intermediary verifies that there are no faults
4. If there is an error the issue is logged
5. If the response is valid then the `timestamp` is attached to the response header considering that there were no faults at the service. If so, then the information about error is attached to the header ass well.
6. The response is handed back to the origin.