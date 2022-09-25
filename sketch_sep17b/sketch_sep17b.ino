#include <Arduino.h>

int ledLike = 2;
int ledBucle = 3;
int led1 = 4;
int led2 = 5;
int led3 = 6;
int led4 = 7;
int led5 = 8;
int buttonLike = 9;
int buttonBucle = 10;
int buttonPrevious = 11;
int buttonNext = 13;
int buttonPlayPause = 12;
int V = 0;
long vol;

void setup() {
  // put your setup code here, to run once:
  pinMode(ledLike, OUTPUT);  
  pinMode(ledBucle, OUTPUT);
  pinMode(led1, OUTPUT);
  pinMode(led2, OUTPUT);
  pinMode(led3, OUTPUT);
  pinMode(led4, OUTPUT);
  pinMode(led5, OUTPUT);
  pinMode(buttonLike, INPUT);
  pinMode(buttonBucle, INPUT);
  pinMode(buttonPrevious, INPUT);
  pinMode(buttonNext, INPUT);
  pinMode(buttonPlayPause, INPUT);
  pinMode(V, OUTPUT);

  Serial.begin(9600);

}

void loop() {
  if(Serial.available()>0){
    int entrada = Serial.read();
    switch(entrada)
    {
      case 'L':
        digitalWrite(ledLike,HIGH);
        break;

      case 'D':
        digitalWrite(ledLike,LOW);
        break;
    }
    }



  
  // put your main code here, to run repeatedly:
  if(digitalRead(buttonLike)== HIGH){
    if (digitalRead(ledLike)== HIGH){
       //Serial.write("NOLike");
       Serial.println("NOLike");
      digitalWrite(ledLike,LOW);
      delay(300);}
    else if(digitalRead(ledLike)== LOW){
      //Serial.write("Like");
      Serial.println("Like");
      digitalWrite(ledLike,HIGH);
      delay(300);
  }
  }
  
if(digitalRead(buttonBucle)== HIGH){
  if (digitalRead(ledBucle)== HIGH){
    //Serial.write("NOBucle");
    Serial.println("NOBucle");
    digitalWrite(ledBucle,LOW);
    delay(300);
}else if(digitalRead(ledBucle)== LOW){
    //Serial.write("Bucle");
    Serial.println("Bucle");
    digitalWrite(ledBucle,HIGH);
    delay(300);
  
  }
}
if(digitalRead(buttonPlayPause)== HIGH){
  //Serial.write("Play");
  Serial.println("Play");
  delay(300);
  
  }
if(digitalRead(buttonPrevious)== HIGH){
  //Serial.write("Previous");
  Serial.println("Previous");
  delay(300);
  
  }
if(digitalRead(buttonNext)== HIGH){
  //Serial.write("Next");
  Serial.println("Next");
  delay(300);
  
  }

int sensorValue = analogRead(V);
vol = analogRead(V); //potenciometro 10k
if (vol >= 0 && vol <= 100){
    digitalWrite(led1, LOW);
    digitalWrite(led2, LOW);
    digitalWrite(led3, LOW);
    digitalWrite(led4, LOW);
    digitalWrite(led5, LOW);
    Serial.println(0);
    delay(1);
 } 
  else if (vol >= 100 && vol <= 200){
    digitalWrite(led1, HIGH);
    digitalWrite(led2, LOW);
    digitalWrite(led3, LOW);
    digitalWrite(led4, LOW);
    digitalWrite(led5, LOW);
    Serial.println(0.2);
    delay(1);
 }
 else if (vol >= 200 && vol <= 300){
    digitalWrite(led1, HIGH);
    digitalWrite(led2, HIGH);
    digitalWrite(led3, LOW);
    digitalWrite(led4, LOW);
    digitalWrite(led5, LOW);
    Serial.println(0.4);
    delay(1);
 }
 else if (vol >= 300 && vol <= 500){
    digitalWrite(led1, HIGH);
    digitalWrite(led2, HIGH);
    digitalWrite(led3, HIGH);
    digitalWrite(led4, LOW);
    digitalWrite(led5, LOW);
    Serial.println(0.6);
    delay(1);
 }
 else if (vol >= 500 && vol <= 700){
    digitalWrite(led1, HIGH);
    digitalWrite(led2, HIGH);
    digitalWrite(led3, HIGH);
    digitalWrite(led4, HIGH);
    digitalWrite(led5, LOW);
    Serial.println(0.8);
    delay(1);
 }
 else{
    digitalWrite(led1, HIGH);
    digitalWrite(led2, HIGH);
    digitalWrite(led3, HIGH);
    digitalWrite(led4, HIGH);
    digitalWrite(led5, HIGH);
    Serial.println(1);
    delay(1);


}  
}
