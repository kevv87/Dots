const int ADP = 9;
const int AA = 2;
const int AB = 3;
const int AC = 4;
const int AD = 5;
const int AE = 6;
const int AF = 7;
const int AG = 8;
const int BDP = 18;
const int BA = 10;
const int BB = 11;
const int BC = 12;
const int BD = 14;
const int BE = 15;
const int BF = 16;
const int BG = 17;

const int LED = 19;
int Byte_entrada = 0;
bool derecha = true;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  pinMode(ADP, OUTPUT);
  pinMode(AA, OUTPUT);
  pinMode(AB, OUTPUT);
  pinMode(AC, OUTPUT);
  pinMode(AD, OUTPUT);
  pinMode(AE, OUTPUT);
  pinMode(AF, OUTPUT);
  pinMode(AG, OUTPUT);
  pinMode(BDP, OUTPUT);
  pinMode(BA, OUTPUT);
  pinMode(BB, OUTPUT);
  pinMode(BC, OUTPUT);
  pinMode(BD, OUTPUT);
  pinMode(BE, OUTPUT);
  pinMode(BF, OUTPUT);
  pinMode(BG, OUTPUT);
  pinMode(LED, OUTPUT);
  reset();
}

void loop() {
  // put your main code here, to run repeatedly:
  if(Serial.available() > 0 && derecha){  // Determina si el puerto serial esta disponible
    Byte_entrada = Serial.read();  // Lee serial
    if(Byte_entrada == 111){
      analogWrite(LED, 255);
    } else if (Byte_entrada == 102){
      analogWrite(LED,0);
    }
    numeros_izq(Byte_entrada);
    derecha = !derecha;
  } else if (Serial.available()>0 && !derecha){
    Byte_entrada = Serial.read();  // Lee serial
    if(Byte_entrada == 111){
      analogWrite(LED, 255);
    } else if (Byte_entrada == 102){
      analogWrite(LED,0);
    } else if(Byte_entrada == 114){
      reset();
    }
    numeros_der(Byte_entrada);
    derecha = !derecha;
  }

}
void reset(){
    digitalWrite(BA, LOW);
    digitalWrite(BB, LOW);
    digitalWrite(BC, LOW);
    analogWrite(BD, 0);
    analogWrite(BE, 0);
    analogWrite(BF, 0);
    analogWrite(BG, 0);
    analogWrite(BDP, HIGH);
    digitalWrite(AA, LOW);
    digitalWrite(AB, LOW);
    digitalWrite(AC, LOW);
    digitalWrite(AD, LOW);
    digitalWrite(AE, LOW);
    digitalWrite(AF, LOW);
    digitalWrite(AG, LOW);
    digitalWrite(ADP, LOW);
    
  }
void numeros_der(int num){
  if (num == '0'){
    digitalWrite(AA, LOW);
    digitalWrite(AB, LOW);
    digitalWrite(AC, LOW);
    digitalWrite(AD, LOW);
    digitalWrite(AE, LOW);
    digitalWrite(AF, LOW);
    digitalWrite(AG, HIGH);
    digitalWrite(ADP, HIGH);
  }else if (num == '1'){
    digitalWrite(AA, HIGH);
    digitalWrite(AB, LOW);
    digitalWrite(AC, LOW);
    digitalWrite(AD, HIGH);
    digitalWrite(AE, HIGH);
    digitalWrite(AF, HIGH);
    digitalWrite(AG, HIGH);
    digitalWrite(ADP, HIGH);
  }else if (num == '2'){
    digitalWrite(AA, LOW);
    digitalWrite(AB, LOW);
    digitalWrite(AC, HIGH);
    digitalWrite(AD, LOW);
    digitalWrite(AE, LOW);
    digitalWrite(AF, HIGH);
    digitalWrite(AG, LOW);
    digitalWrite(ADP, HIGH);
  }else if (num == '3'){
    digitalWrite(AA, LOW);
    digitalWrite(AB, LOW);
    digitalWrite(AC, LOW);
    digitalWrite(AD, LOW);
    digitalWrite(AE, HIGH);
    digitalWrite(AF, HIGH);
    digitalWrite(AG, LOW);
    digitalWrite(ADP, HIGH);
  }else if (num == '4'){
    digitalWrite(AA, HIGH);
    digitalWrite(AB, LOW);
    digitalWrite(AC, LOW);
    digitalWrite(AD, HIGH);
    digitalWrite(AE, HIGH);
    digitalWrite(AF, LOW);
    digitalWrite(AG, LOW);
    digitalWrite(ADP, HIGH);
  }else if (num == '5'){
    digitalWrite(AA, LOW);
    digitalWrite(AB, HIGH);
    digitalWrite(AC, LOW);
    digitalWrite(AD, LOW);
    digitalWrite(AE, HIGH);
    digitalWrite(AF, LOW);
    digitalWrite(AG, LOW);
    digitalWrite(ADP, HIGH);
  }else if (num == '6'){
    digitalWrite(AA, LOW);
    digitalWrite(AB, HIGH);
    digitalWrite(AC, LOW);
    digitalWrite(AD, LOW);
    digitalWrite(AE, LOW);
    digitalWrite(AF, LOW);
    digitalWrite(AG, LOW);
    digitalWrite(ADP, HIGH);
  }else if (num == '7'){
    digitalWrite(AA, LOW);
    digitalWrite(AB, LOW);
    digitalWrite(AC, LOW);
    digitalWrite(AD, HIGH);
    digitalWrite(AE, HIGH);
    digitalWrite(AF, HIGH);
    digitalWrite(AG, HIGH);
    digitalWrite(ADP, HIGH);
  }else if (num == '8'){
    digitalWrite(AA, LOW);
    digitalWrite(AB, LOW);
    digitalWrite(AC, LOW);
    digitalWrite(AD, LOW);
    digitalWrite(AE, LOW);
    digitalWrite(AF, LOW);
    digitalWrite(AG, LOW);
    digitalWrite(ADP, HIGH);
  }else if (num == '9'){
    digitalWrite(AA, LOW);
    digitalWrite(AB, LOW);
    digitalWrite(AC, LOW);
    digitalWrite(AD, LOW);
    digitalWrite(AE, HIGH);
    digitalWrite(AF, LOW);
    digitalWrite(AG, LOW);
    digitalWrite(ADP, HIGH);
  }
}

void numeros_izq(int num){
  if (num == '0'){
    digitalWrite(BA, LOW);
    digitalWrite(BB, LOW);
    analogWrite(BC, 0);
    analogWrite(BD, 0);
    analogWrite(BE, 0);
    analogWrite(BF, 0);
    analogWrite(BG, 255);
    analogWrite(BDP, 255);
  }else if(num == '1'){
    digitalWrite(BA, HIGH);
    digitalWrite(BB, LOW);
    digitalWrite(BC, LOW);
    analogWrite(BD, 255);
    analogWrite(BE, 255);
    analogWrite(BF, 255);
    analogWrite(BG, 255);
    analogWrite(BDP, 255);
  }else if (num == '2'){
    digitalWrite(BA, LOW);
    digitalWrite(BB, LOW);
    digitalWrite(BC, HIGH);
    analogWrite(BD, 0);
    analogWrite(BE, 0);
    analogWrite(BF, 255);
    analogWrite(BG, 0);
    analogWrite(BDP, 255);
  }else if (num == '3'){
    digitalWrite(BA, LOW);
    digitalWrite(BB, LOW);
    digitalWrite(BC, LOW);
    analogWrite(BD, 0);
    analogWrite(BE, 255);
    analogWrite(BF, 255);
    analogWrite(BG, 0);
    analogWrite(BDP, 255);
  }else if (num == '4'){
    digitalWrite(BA, HIGH);
    digitalWrite(BB, LOW);
    digitalWrite(BC, LOW);
    analogWrite(BD, 255);
    analogWrite(BE, 255);
    analogWrite(BF, 0);
    analogWrite(BG, 0);
    analogWrite(BDP, 255);
  }else if (num == '5'){
    digitalWrite(BA, LOW);
    digitalWrite(BB, HIGH);
    digitalWrite(BC, LOW);
    analogWrite(BD, 0);
    analogWrite(BE, 255);
    analogWrite(BF, 0);
    analogWrite(BG, 0);
    analogWrite(BDP, 255);
  }else if (num == '6'){
    digitalWrite(BA, LOW);
    digitalWrite(BB, HIGH);
    digitalWrite(BC, LOW);
    analogWrite(BD, 0);
    analogWrite(BE, 0);
    analogWrite(BF, 0);
    analogWrite(BG, 0);
    analogWrite(BDP, 255);
  }else if (num == '7'){
    digitalWrite(BA, LOW);
    digitalWrite(BB, LOW);
    digitalWrite(BC, LOW);
    analogWrite(BD, 255);
    analogWrite(BE, 255);
    analogWrite(BF, 255);
    analogWrite(BG, 255);
    analogWrite(BDP, 255);
  }else if (num == '8'){
    digitalWrite(BA, LOW);
    digitalWrite(BB, LOW);
    digitalWrite(BC, LOW);
    analogWrite(BD, 0);
    analogWrite(BE, 0);
    analogWrite(BF, 0);
    analogWrite(BG, 0);
    analogWrite(BDP, HIGH);
  }else if (num == '9'){
    digitalWrite(BA, LOW);
    digitalWrite(BB, LOW);
    digitalWrite(BC, LOW);
    analogWrite(BD, 0);
    analogWrite(BE, 255);
    analogWrite(BF, 0);
    analogWrite(BG, 0);
    analogWrite(BDP, 255);
  }
}
