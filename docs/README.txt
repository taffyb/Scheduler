I have utilised a Singleton pattern for the Gateway as there should only be one of those.

The Resources process messages in their own thread so processing is non blocking

alternate Scheduler implementations are possible and can be specified on the command line.

It is possible to specify a termination time - after this period a Terminate command is issued and only messages from the active group will be processed.

for the purpose of testing the SchedulerHarness was created. There are a fixed set of messages which are loaded when the harness is started.

