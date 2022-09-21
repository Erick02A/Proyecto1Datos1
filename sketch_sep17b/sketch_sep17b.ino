#include <Arduino.h>

int button1 = 2;
int button2 = 4;

void setup(){
  pinMode(button1, INPUT);
  pinMode(button2, INPUT);
  Serial.begin(9600);

}

void loop() {
  // put your main code here, to run repeatedly:
  if(digitalRead(button1)== HIGH){
  Serial.write("Saludos");
  delay(300);
  }
  if(digitalRead(button2)== HIGH){
  Serial.write("S");
  delay(300);
  }
  else{
  //nothin`
    }
  if(!Serial.available()){
    return;
    }
}
