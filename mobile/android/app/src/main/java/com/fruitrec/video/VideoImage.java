package com.fruitrec.video;

public class VideoImage
{
  private int _BlockWidth = 8;
  
  private int _WidthCif = 88;
  private int _HeightCif = 72;
  
  private int _WidthVga = 160;
  private int _HeightVga = 120;
  
  private int _TableQuantization = 31;
  
  private int FIX_0_298631336 = 2446;
  private int FIX_0_390180644 = 3196;
  private int FIX_0_541196100 = 4433;
  private int FIX_0_765366865 = 6270;
  private int FIX_0_899976223 = 7373;
  private int FIX_1_175875602 = 9633;
  private int FIX_1_501321110 = 12299;
  private int FIX_1_847759065 = 15137;
  private int FIX_1_961570560 = 16069;
  private int FIX_2_053119869 = 16819;
  private int FIX_2_562915447 = 20995;
  private int FIX_3_072711026 = 25172;
  
  private int _BITS = 13;
  private int PASS1_BITS = 1;
  private int F1 = this._BITS - this.PASS1_BITS - 1;
  private int F2 = this._BITS - this.PASS1_BITS;
  private int F3 = this._BITS + this.PASS1_BITS + 3;

  private short[] dataBlockBuffer = new short[64];
  
  private short[] zigZagPositions = { 0, 1, 8, 16, 9, 2, 3, 10, 
    17, 24, 32, 25, 18, 11, 4, 5, 12, 19, 26, 33, 40, 48, 41, 34, 27, 
    20, 13, 6, 7, 14, 21, 28, 35, 42, 49, 56, 57, 50, 43, 36, 29, 22, 
    15, 23, 30, 37, 44, 51, 58, 59, 52, 45, 38, 31, 39, 46, 53, 60, 61, 
    54, 47, 55, 62, 63 };

  private short[] quantizerValues = { 3, 5, 7, 9, 11, 13, 15, 17, 
    5, 7, 9, 11, 13, 15, 17, 19, 7, 9, 11, 13, 15, 17, 19, 21, 9, 11, 
    13, 15, 17, 19, 21, 23, 11, 13, 15, 17, 19, 21, 23, 25, 13, 15, 17, 
    19, 21, 23, 25, 27, 15, 17, 19, 21, 23, 25, 27, 29, 17, 19, 21, 23, 
    25, 27, 29, 31 };
  
  static byte[] clzlut = { 8, 7, 6, 6, 5, 5, 5, 5, 4, 4, 4, 4, 4, 
    4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 
    2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 
    2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
    1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
    1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
    1, 1, 1, 1, 1 };
  
  private uint StreamField;
  
  private int StreamFieldBitIndex;
  
  private int StreamIndex;
  
  private int SliceCount;
  private boolean PictureComplete;
  private int PictureFormat;
  private int Resolution;
  private int PictureType;
  private int QuantizerMode;
  
  public int getSliceCount()
  {
    return this.SliceCount;
  }

  public int getPictureType()
  {
    return this.PictureType;
  }

  public int getFrameIndex()
  {
    return this.FrameIndex;
  }

  public int getPixelRowSize()
  {
    return this.PixelRowSize;
  }

  public uint[] getPixelData()
  {
    return this.PixelData;
  }

  private int FrameIndex;

  private int SliceIndex;
  
  private int BlockCount;
  
  private int Width;
  
  private int Height;
  
  private int PixelRowSize;
  
  private byte[] ImageStream;
  
  private ImageSlice ImageSlice;
  
  private uint[] PixelData;
  
  public void AddImageStream(byte[] stream)
  {
    this.ImageStream = stream;
    ProcessStream();
  }

  private void ProcessStream()
  {
    boolean blockY0HasAcComponents = false;
    boolean blockY1HasAcComponents = false;
    boolean blockY2HasAcComponents = false;
    boolean blockY3HasAcComponents = false;
    boolean blockCbHasAcComponents = false;
    boolean blockCrHasAcComponents = false;

    this.StreamFieldBitIndex = 32;
    this.StreamField = new uint(0);
    this.StreamIndex = 0;
    this.SliceIndex = 0;
    this.PictureComplete = false;

    while ((!this.PictureComplete) && (this.StreamIndex < this.ImageStream.length >> 2)) {
      ReadHeader();
      
      if (!this.PictureComplete) {
        for (int count = 0; count < this.BlockCount; count++) {
          uint macroBlockEmpty = ReadStreamData(1);
          
          if (macroBlockEmpty.intValue() == 0) {
            uint acCoefficients = ReadStreamData(8);
            
            blockY0HasAcComponents = acCoefficients.shiftRight(0)
              .and(1).intValue() == 1;
            blockY1HasAcComponents = acCoefficients.shiftRight(1)
              .and(1).intValue() == 1;
            blockY2HasAcComponents = acCoefficients.shiftRight(2)
              .and(1).intValue() == 1;
            blockY3HasAcComponents = acCoefficients.shiftRight(3)
              .and(1).intValue() == 1;
            blockCbHasAcComponents = acCoefficients.shiftRight(4)
              .and(1).intValue() == 1;
            blockCrHasAcComponents = acCoefficients.shiftRight(5)
              .and(1).intValue() == 1;
            
            if (acCoefficients.shiftRight(6).and(1).intValue() == 1) {
              uint quantizerMode = ReadStreamData(2);
              this.QuantizerMode = (quantizerMode.intValue() < 2 ? quantizerMode
                .flipBits() : 
                quantizerMode.intValue());
            }

            GetBlockBytes(blockY0HasAcComponents);
            InverseTransform(count, 0);
            

            GetBlockBytes(blockY1HasAcComponents);
            InverseTransform(count, 1);

            GetBlockBytes(blockY2HasAcComponents);
            InverseTransform(count, 2);

            GetBlockBytes(blockY3HasAcComponents);
            InverseTransform(count, 3);

            GetBlockBytes(blockCbHasAcComponents);
            InverseTransform(count, 4);

            GetBlockBytes(blockCrHasAcComponents);
            InverseTransform(count, 5);
          }
        }

        ComposeImageSlice();
      }
    }
  }
  
















  private void ReadHeader()
  {
    uint code = new uint(0);
    uint startCode = new uint(0);
    
    AlignStreamData();
    
    code = ReadStreamData(22);
    
    startCode = new uint(code.and(-32));
    
    if (startCode.intValue() == 32) {
      if (code.and(31).intValue() == 31) {
        this.PictureComplete = true;
      }
      else if (this.SliceIndex++ == 0) {
        this.PictureFormat = ReadStreamData(2).intValue();
        this.Resolution = ReadStreamData(3).intValue();
        this.PictureType = ReadStreamData(3).intValue();
        this.QuantizerMode = ReadStreamData(5).intValue();
        this.FrameIndex = ReadStreamData(32).intValue();
        
        switch (this.PictureFormat) {
        case 1: 
          this.Width = (this._WidthCif << this.Resolution - 1);
          this.Height = (this._HeightCif << this.Resolution - 1);
          break;
        case 2: 
          this.Width = (this._WidthVga << this.Resolution - 1);
          this.Height = (this._HeightVga << this.Resolution - 1);
        }
        
        

        this.PixelRowSize = (this.Width << 1);
        
        this.SliceCount = (this.Height >> 4);
        this.BlockCount = (this.Width >> 4);
        
        if (this.ImageSlice == null) {
          this.ImageSlice = new ImageSlice(this.BlockCount);
          this.PixelData = new uint[this.Width * this.Height];



        }
        else if (this.ImageSlice.MacroBlocks.length != this.BlockCount) {
          this.ImageSlice = new ImageSlice(this.BlockCount);
          this.PixelData = new uint[this.Width * this.Height];
        }
        

      }
      else
      {
        this.QuantizerMode = ReadStreamData(5).intValue();
      }
    }
  }
  
  private void GetBlockBytes(boolean acCoefficientsAvailable)
  {
    int[] run = new int[1];
    int[] level = new int[1];
    int zigZagPosition = 0;
    int matrixPosition = 0;
    boolean[] last = new boolean[1];
    
    for (int i = 0; i < this.dataBlockBuffer.length; i++) {
      this.dataBlockBuffer[i] = 0;
    }
    

    uint dcCoefficient = ReadStreamData(10);
    
    if (this.QuantizerMode == this._TableQuantization) {
      this.dataBlockBuffer[0] = dcCoefficient
        .times(this.quantizerValues[0]);
      
      if (acCoefficientsAvailable) {
        DecodeFieldBytes(run, level, last);

        //while (last[0] == 0) {
        while (last[0] == false) {
          zigZagPosition += run[0] + 1;
          matrixPosition = this.zigZagPositions[zigZagPosition];
          level[0] *= this.quantizerValues[matrixPosition];
          this.dataBlockBuffer[matrixPosition] = ((short)level[0]);
          DecodeFieldBytes(run, level, last);
        }
      }
    }
    else {
      throw new RuntimeException(
        "ant quantizer mode is not yet implemented.");
    }
  }
  
  private void DecodeFieldBytes(int[] run, int[] level, boolean[] last) {
    uint streamCode = new uint(0);
    
    int streamLength = 0;
    
    int zeroCount = 0;
    int temp = 0;
    int sign = 0;
    










    streamCode = PeekStreamData(this.ImageStream, 32);
    











    zeroCount = CountLeadingZeros(streamCode);
    streamCode.shiftLeftEquals(zeroCount + 1);
    
    streamLength += zeroCount + 1;
    


    if (zeroCount > 1) {
      temp = streamCode.shiftRight(32 - (zeroCount - 1)).intValue();
      















      streamCode.shiftLeftEquals(zeroCount - 1);
      




      streamLength += zeroCount - 1;
      

      run[0] = (temp + (1 << zeroCount - 1));
    }
    else {
      run[0] = zeroCount;
    }
    













    zeroCount = CountLeadingZeros(streamCode);
    streamCode.shiftLeftEquals(zeroCount + 1);
    streamLength += zeroCount + 1;
    


    if (zeroCount == 1)
    {


      run[0] = 0;
      last[0] = true;
    } else {
      if (zeroCount == 0) {
        zeroCount = 1;
        temp = 1;
      }
      
      streamLength += zeroCount;
      

      streamCode.shiftRightEquals(32 - zeroCount);
      





      sign = streamCode.and(1).intValue();
      

      if (zeroCount != 0)
      {



        temp = streamCode.shiftRight(1).intValue();
        



        temp += (1 << zeroCount - 1);
      }
      

      level[0] = (sign == 1 ? -temp : temp);
      
      last[0] = false;
    }
    


    ReadStreamData(streamLength);
  }
  
  int numCalls = 0;
  
  private uint ReadStreamData(int count) {
    uint data = new uint(0);
    
    while (count > 32 - this.StreamFieldBitIndex) {
      data = 
        data.shiftLeft(32 - this.StreamFieldBitIndex).or(this.StreamField.shiftRight(this.StreamFieldBitIndex));
      
      count -= 32 - this.StreamFieldBitIndex;
      
      this.StreamField = new uint(this.ImageStream, this.StreamIndex * 4);
      
      this.StreamFieldBitIndex = 0;
      this.StreamIndex += 1;
    }
    
    if (count > 0) {
      data = data.shiftLeft(count).or(this.StreamField.shiftRight(32 - count));
      
      this.StreamField.shiftLeftEquals(count);
      this.StreamFieldBitIndex += count;
    }
    
    this.numCalls += 1;
    


    return data;
  }
  
  private uint PeekStreamData(byte[] stream, int count) {
    uint data = new uint(0);
    uint streamField = this.StreamField;
    int streamFieldBitIndex = this.StreamFieldBitIndex;
    
    while ((count > 32 - streamFieldBitIndex) && 
      (this.StreamIndex < this.ImageStream.length >> 2)) {
      data = data.shiftLeft(32 - streamFieldBitIndex).or(streamField
        .shiftRight(streamFieldBitIndex));
      
      count -= 32 - streamFieldBitIndex;
      
      streamField = new uint(stream, this.StreamIndex * 4);
      streamFieldBitIndex = 0;
    }
    
    if (count > 0) {
      data = data.shiftLeft(count).or(
        streamField.shiftRight(32 - count));
    }
    
    return data;
  }
  


  private void AlignStreamData()
  {
    int actualLength = this.StreamFieldBitIndex;
    
    if (actualLength > 0) {
      int alignedLength = actualLength & 0xFFFFFFF8;
      if (alignedLength != actualLength) {
        alignedLength += 8;
        this.StreamField.shiftLeftEquals(alignedLength - actualLength);
        this.StreamFieldBitIndex = alignedLength;
      }
    }
  }
  






























































































































































  private void ComposeImageSlice()
  {
    int lumaElementIndex1 = 0;
    int lumaElementIndex2 = 0;
    int chromaOffset = 0;
    
    int dataIndex1 = 0;
    int dataIndex2 = 0;
    
    int lumaElementValue1 = 0;
    int lumaElementValue2 = 0;
    int chromaBlueValue = 0;
    int chromaRedValue = 0;
    
    int[] cromaQuadrantOffsets = { 0, 4, 32, 36 };
    int[] pixelDataQuadrantOffsets = { 0, this._BlockWidth, 
      this.Width * this._BlockWidth, this.Width * this._BlockWidth + this._BlockWidth };
    
    int imageDataOffset = (this.SliceIndex - 1) * this.Width * 16;
    MacroBlock[] arrayOfMacroBlock;
    int j = (arrayOfMacroBlock = this.ImageSlice.MacroBlocks).length; for (int i = 0; i < j; i++) { MacroBlock macroBlock = arrayOfMacroBlock[i];
      for (int verticalStep = 0; verticalStep < this._BlockWidth / 2; verticalStep++) {
        chromaOffset = verticalStep * this._BlockWidth;
        lumaElementIndex1 = verticalStep * this._BlockWidth * 2;
        lumaElementIndex2 = lumaElementIndex1 + this._BlockWidth;
        
        dataIndex1 = imageDataOffset + 2 * verticalStep * this.Width;
        dataIndex2 = dataIndex1 + this.Width;
        
        for (int horizontalStep = 0; horizontalStep < this._BlockWidth / 2; horizontalStep++) {
          for (int quadrant = 0; quadrant < 4; quadrant++) {
            int chromaIndex = chromaOffset + 
              cromaQuadrantOffsets[quadrant] + 
              horizontalStep;
            chromaBlueValue = macroBlock.DataBlocks[4][chromaIndex];
            chromaRedValue = macroBlock.DataBlocks[5][chromaIndex];
            
            int u = chromaBlueValue - 128;
            int ug = 88 * u;
            int ub = 454 * u;
            
            int v = chromaRedValue - 128;
            int vg = 183 * v;
            int vr = 359 * v;
            
            for (int pixel = 0; pixel < 2; pixel++) {
              int deltaIndex = 2 * horizontalStep + pixel;
              lumaElementValue1 = macroBlock.DataBlocks[quadrant][
                (lumaElementIndex1 + deltaIndex)] << 8;
              lumaElementValue2 = macroBlock.DataBlocks[quadrant][
                (lumaElementIndex2 + deltaIndex)] << 8;
              
              int r = Saturate5(lumaElementValue1 + vr);
              int g = Saturate6(lumaElementValue1 - ug - vg);
              int b = Saturate5(lumaElementValue1 + ub);
              
              this.PixelData[
              
                (dataIndex1 + pixelDataQuadrantOffsets[quadrant] + deltaIndex)] = MakeRgb(r, g, b);
              
              r = Saturate5(lumaElementValue2 + vr);
              g = Saturate6(lumaElementValue2 - ug - vg);
              b = Saturate5(lumaElementValue2 + ub);
              
              this.PixelData[
              
                (dataIndex2 + pixelDataQuadrantOffsets[quadrant] + deltaIndex)] = MakeRgb(r, g, b);
            }
          }
        }
      }
      
      imageDataOffset += 16;
    }
  }
  
  private int Saturate5(int x) {
    if (x < 0) {
      x = 0;
    }
    
    x >>= 11;
    
    return x > 31 ? 31 : x;
  }
  
  private int Saturate6(int x) {
    if (x < 0) {
      x = 0;
    }
    
    x >>= 10;
    
    return x > 63 ? 63 : x;
  }
  
  private uint MakeRgb(int r, int g, int b) {
    r <<= 2;
    g <<= 1;
    b <<= 2;
    
    uint ru = new uint(r);
    uint gu = new uint(g);
    uint bu = new uint(b);
    
    uint retval = ru.shiftLeft(16);
    retval = retval.or(gu.shiftLeft(8));
    retval = retval.or(bu);
    
    return retval;
  }
  
  private int CountLeadingZeros(uint value)
  {
    int accum = 0;
    
    accum += clzlut[value.shiftRight(24).intValue()];
    if (accum == 8) {
      accum += clzlut[(value.shiftRight(16).intValue() & 0xFF)];
    }
    if (accum == 16) {
      accum += clzlut[(value.shiftRight(8).intValue() & 0xFF)];
    }
    if (accum == 24) {
      accum += clzlut[(value.intValue() & 0xFF)];
    }
    
    return accum;
  }
  

  void InverseTransform(int macroBlockIndex, int dataBlockIndex)
  {
    int[] workSpace = new int[64];
    short[] data = new short[64];
    




    int pointer = 0;
    
    for (int index = 8; index > 0; index--) {
      if ((this.dataBlockBuffer[(pointer + 8)] == 0) && 
        (this.dataBlockBuffer[(pointer + 16)] == 0) && 
        (this.dataBlockBuffer[(pointer + 24)] == 0) && 
        (this.dataBlockBuffer[(pointer + 32)] == 0) && 
        (this.dataBlockBuffer[(pointer + 40)] == 0) && 
        (this.dataBlockBuffer[(pointer + 48)] == 0) && 
        (this.dataBlockBuffer[(pointer + 56)] == 0)) {
        int dcValue = this.dataBlockBuffer[pointer] << this.PASS1_BITS;
        
        workSpace[(pointer + 0)] = dcValue;
        workSpace[(pointer + 8)] = dcValue;
        workSpace[(pointer + 16)] = dcValue;
        workSpace[(pointer + 24)] = dcValue;
        workSpace[(pointer + 32)] = dcValue;
        workSpace[(pointer + 40)] = dcValue;
        workSpace[(pointer + 48)] = dcValue;
        workSpace[(pointer + 56)] = dcValue;
        
        pointer++;
      }
      else
      {
        int z2 = this.dataBlockBuffer[(pointer + 16)];
        int z3 = this.dataBlockBuffer[(pointer + 48)];
        
        int z1 = (z2 + z3) * this.FIX_0_541196100;
        int tmp2 = z1 + z3 * -this.FIX_1_847759065;
        int tmp3 = z1 + z2 * this.FIX_0_765366865;
        
        z2 = this.dataBlockBuffer[pointer];
        z3 = this.dataBlockBuffer[(pointer + 32)];
        
        int tmp0 = z2 + z3 << this._BITS;
        int tmp1 = z2 - z3 << this._BITS;
        
        int tmp10 = tmp0 + tmp3;
        int tmp13 = tmp0 - tmp3;
        int tmp11 = tmp1 + tmp2;
        int tmp12 = tmp1 - tmp2;
        
        tmp0 = this.dataBlockBuffer[(pointer + 56)];
        tmp1 = this.dataBlockBuffer[(pointer + 40)];
        tmp2 = this.dataBlockBuffer[(pointer + 24)];
        tmp3 = this.dataBlockBuffer[(pointer + 8)];
        
        z1 = tmp0 + tmp3;
        z2 = tmp1 + tmp2;
        z3 = tmp0 + tmp2;
        int z4 = tmp1 + tmp3;
        int z5 = (z3 + z4) * this.FIX_1_175875602;
        
        tmp0 *= this.FIX_0_298631336;
        tmp1 *= this.FIX_2_053119869;
        tmp2 *= this.FIX_3_072711026;
        tmp3 *= this.FIX_1_501321110;
        z1 *= -this.FIX_0_899976223;
        z2 *= -this.FIX_2_562915447;
        z3 *= -this.FIX_1_961570560;
        z4 *= -this.FIX_0_390180644;
        
        z3 += z5;
        z4 += z5;
        
        tmp0 += z1 + z3;
        tmp1 += z2 + z4;
        tmp2 += z2 + z3;
        tmp3 += z1 + z4;
        
        workSpace[(pointer + 0)] = (tmp10 + tmp3 + (1 << this.F1) >> this.F2);
        workSpace[(pointer + 56)] = (tmp10 - tmp3 + (1 << this.F1) >> this.F2);
        workSpace[(pointer + 8)] = (tmp11 + tmp2 + (1 << this.F1) >> this.F2);
        workSpace[(pointer + 48)] = (tmp11 - tmp2 + (1 << this.F1) >> this.F2);
        workSpace[(pointer + 16)] = (tmp12 + tmp1 + (1 << this.F1) >> this.F2);
        workSpace[(pointer + 40)] = (tmp12 - tmp1 + (1 << this.F1) >> this.F2);
        workSpace[(pointer + 24)] = (tmp13 + tmp0 + (1 << this.F1) >> this.F2);
        workSpace[(pointer + 32)] = (tmp13 - tmp0 + (1 << this.F1) >> this.F2);
        
        pointer++;
      }
    }
    pointer = 0;
    
    for (int index = 0; index < 8; index++) {
      int z2 = workSpace[(pointer + 2)];
      int z3 = workSpace[(pointer + 6)];
      
      int z1 = (z2 + z3) * this.FIX_0_541196100;
      int tmp2 = z1 + z3 * -this.FIX_1_847759065;
      int tmp3 = z1 + z2 * this.FIX_0_765366865;
      
      int tmp0 = workSpace[(pointer + 0)] + workSpace[(pointer + 4)] << this._BITS;
      int tmp1 = workSpace[(pointer + 0)] - workSpace[(pointer + 4)] << this._BITS;
      
      int tmp10 = tmp0 + tmp3;
      int tmp13 = tmp0 - tmp3;
      int tmp11 = tmp1 + tmp2;
      int tmp12 = tmp1 - tmp2;
      
      tmp0 = workSpace[(pointer + 7)];
      tmp1 = workSpace[(pointer + 5)];
      tmp2 = workSpace[(pointer + 3)];
      tmp3 = workSpace[(pointer + 1)];
      
      z1 = tmp0 + tmp3;
      z2 = tmp1 + tmp2;
      z3 = tmp0 + tmp2;
      int z4 = tmp1 + tmp3;
      
      int z5 = (z3 + z4) * this.FIX_1_175875602;
      
      tmp0 *= this.FIX_0_298631336;
      tmp1 *= this.FIX_2_053119869;
      tmp2 *= this.FIX_3_072711026;
      tmp3 *= this.FIX_1_501321110;
      z1 *= -this.FIX_0_899976223;
      z2 *= -this.FIX_2_562915447;
      z3 *= -this.FIX_1_961570560;
      z4 *= -this.FIX_0_390180644;
      
      z3 += z5;
      z4 += z5;
      
      tmp0 += z1 + z3;
      tmp1 += z2 + z4;
      tmp2 += z2 + z3;
      tmp3 += z1 + z4;
      
      data[(pointer + 0)] = ((short)(tmp10 + tmp3 >> this.F3));
      data[(pointer + 7)] = ((short)(tmp10 - tmp3 >> this.F3));
      data[(pointer + 1)] = ((short)(tmp11 + tmp2 >> this.F3));
      data[(pointer + 6)] = ((short)(tmp11 - tmp2 >> this.F3));
      data[(pointer + 2)] = ((short)(tmp12 + tmp1 >> this.F3));
      data[(pointer + 5)] = ((short)(tmp12 - tmp1 >> this.F3));
      data[(pointer + 3)] = ((short)(tmp13 + tmp0 >> this.F3));
      data[(pointer + 4)] = ((short)(tmp13 - tmp0 >> this.F3));
      
      pointer += 8;
    }
    
    for (int i = 0; i < data.length; i++) {
      this.ImageSlice.MacroBlocks[macroBlockIndex].DataBlocks[dataBlockIndex][i] = data[i];
    }
  }
}
