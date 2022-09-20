#include <Arduino.h>

void setup() {
  // put your setup code here, to run once:
  pinMode(2, INPUT);
  pinMode(3, INPUT);
  Serial.begin(9600);

}

void loop() {
  // put your main code here, to run repeatedly:
  if(digitalRead(2)== HIGH){
  Serial.write("Saludos");
  delay(300);
}if(digitalRead(3)== HIGH){
  Serial.write("Saldyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyygd");
  delay(300);
}
else{
  //nothin
  }
  if(!Serial.available()){
    return;
    }
}
