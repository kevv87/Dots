const int dp = 13;
const int AA = 2;
const int AB = 5;
const int AC = 4;
const int AD = 6;
const int AE = 7;
const int AF = 9;
const int AG = 8;
const int LED = 10;
int Byte_entrada = 0;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  pinMode(dp, OUTPUT);
  pinMode(AA, OUTPUT);
  pinMode(AB, OUTPUT);
  pinMode(AC, OUTPUT);
  pinMode(AD, OUTPUT);
  pinMode(AE, OUTPUT);
  pinMode(AF, OUTPUT);
  pinMode(AG, OUTPUT);
  pinMode(LED, OUTPUT);
  numeros('0');
}

void loop() {
  // put your main code here, to run repeatedly:
  if(Serial.available() > 0){  // Determina si el puerto serial esta disponible
    Byte_entrada = Serial.read();  // Lee serial
    Serial.write(Byte_entrada+"/n");
    Serial.write(Byte_entrada/10+"/n");
  }

}

void numeros(int num){   
  if (num == '0'){
    digitalWrite(AA, LOW);
    digitalWrite(AB, LOW);
    digitalWrite(AC, LOW);
    digitalWrite(AD, LOW);
    digitalWrite(AE, LOW);
    digitalWrite(AF, LOW);
    digitalWrite(AG, HIGH);
    digitalWrite(dp, HIGH);
  }else if (num == '1'){
    digitalWrite(AA, HIGH);
    digitalWrite(AB, LOW);
    digitalWrite(AC, LOW);
    digitalWrite(AD, HIGH);
    digitalWrite(AE, HIGH);
    digitalWrite(AF, HIGH);
    digitalWrite(AG, HIGH);
    digitalWrite(dp, HIGH);
  }else if (num == '2'){
    digitalWrite(AA, LOW);
    digitalWrite(AB, LOW);
    digitalWrite(AC, HIGH);
    digitalWrite(AD, LOW);
    digitalWrite(AE, LOW);
    digitalWrite(AF, HIGH);
    digitalWrite(AG, LOW);
    digitalWrite(dp, HIGH);
  }else if (num == '3'){
    digitalWrite(AA, LOW);
    digitalWrite(AB, LOW);
    digitalWrite(AC, LOW);
    digitalWrite(AD, LOW);
    digitalWrite(AE, HIGH);
    digitalWrite(AF, HIGH);
    digitalWrite(AG, LOW);
    digitalWrite(dp, HIGH);
  }else if (num == '4'){
    digitalWrite(AA, HIGH);
    digitalWrite(AB, LOW);
    digitalWrite(AC, LOW);
    digitalWrite(AD, HIGH);
    digitalWrite(AE, HIGH);
    digitalWrite(AF, LOW);
    digitalWrite(AG, LOW);
    digitalWrite(dp, HIGH);
  }else if (num == '5'){
    digitalWrite(AA, LOW);
    digitalWrite(AB, HIGH);
    digitalWrite(AC, LOW);
    digitalWrite(AD, LOW);
    digitalWrite(AE, HIGH);
    digitalWrite(AF, LOW);
    digitalWrite(AG, LOW);
    digitalWrite(dp, HIGH);
  }else if (num == '6'){
    digitalWrite(AA, LOW);
    digitalWrite(AB, HIGH);
    digitalWrite(AC, LOW);
    digitalWrite(AD, LOW);
    digitalWrite(AE, LOW);
    digitalWrite(AF, LOW);
    digitalWrite(AG, LOW);
    digitalWrite(dp, HIGH);
  }else if (num == '7'){
    digitalWrite(AA, LOW);
    digitalWrite(AB, LOW);
    digitalWrite(AC, LOW);
    digitalWrite(AD, HIGH);
    digitalWrite(AE, HIGH);
    digitalWrite(AF, HIGH);
    digitalWrite(AG, HIGH);
    digitalWrite(dp, HIGH);
  }else if (num == '8'){
    digitalWrite(AA, LOW);
    digitalWrite(AB, LOW);
    digitalWrite(AC, LOW);
    digitalWrite(AD, LOW);
    digitalWrite(AE, LOW);
    digitalWrite(AF, LOW);
    digitalWrite(AG, LOW);
    digitalWrite(dp, HIGH);
  }else if (num == '9'){
    digitalWrite(AA, LOW);
    digitalWrite(AB, LOW);
    digitalWrite(AC, LOW);
    digitalWrite(AD, LOW);
    digitalWrite(AE, HIGH);
    digitalWrite(AF, LOW);
    digitalWrite(AG, LOW);
    digitalWrite(dp, HIGH);
  }
}
