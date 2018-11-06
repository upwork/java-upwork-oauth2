# Makefile for Upwork API java example
#

SRC_PATH   = src
SRC_NAME   = TestApi
SRC        = $(SRC_PATH)/$(SRC_NAME).java
LIB_DIR    = ./../lib
CLASSPATH  = "$(LIB_DIR)/google-oauth-client-1.25.0/google-http-client-1.25.0.jar:$(LIB_DIR)/google-oauth-client-1.25.0/google-http-client-jackson2-1.25.0.jar:$(LIB_DIR)/google-oauth-client-1.25.0/google-oauth-client-1.25.0.jar:$(LIB_DIR)/google-oauth-client-1.25.0/jackson-core-2.9.6.jar:$(LIB_DIR)/google-oauth-client-1.25.0/jsr305-3.0.2.jar:$(LIB_DIR)/java-json.jar:$(LIB_DIR)/java-upwork.jar:./$(SRC_PATH)"

compile :
	javac -cp $(CLASSPATH) $(SRC)

run :
	java -cp $(CLASSPATH) $(SRC_NAME)
