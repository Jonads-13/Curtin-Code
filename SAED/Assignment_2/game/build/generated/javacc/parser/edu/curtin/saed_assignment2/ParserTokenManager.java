/* Generated By:JavaCC: Do not edit this line. ParserTokenManager.java */
package edu.curtin.saed_assignment2;

/** Token Manager. */
public class ParserTokenManager implements ParserConstants
{

  /** Debug output. */
  public static  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public static  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private static final int jjStopStringLiteralDfa_0(int pos, long active0)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0xbfe0L) != 0L)
         {
            jjmatchedKind = 17;
            return 26;
         }
         return -1;
      case 1:
         if ((active0 & 0x1000L) != 0L)
            return 26;
         if ((active0 & 0xafe0L) != 0L)
         {
            jjmatchedKind = 17;
            jjmatchedPos = 1;
            return 26;
         }
         return -1;
      case 2:
         if ((active0 & 0xafe0L) != 0L)
         {
            jjmatchedKind = 17;
            jjmatchedPos = 2;
            return 26;
         }
         return -1;
      case 3:
         if ((active0 & 0x1a0L) != 0L)
            return 26;
         if ((active0 & 0xae40L) != 0L)
         {
            jjmatchedKind = 17;
            jjmatchedPos = 3;
            return 26;
         }
         return -1;
      case 4:
         if ((active0 & 0x40L) != 0L)
            return 26;
         if ((active0 & 0xae00L) != 0L)
         {
            jjmatchedKind = 17;
            jjmatchedPos = 4;
            return 26;
         }
         return -1;
      case 5:
         if ((active0 & 0xc00L) != 0L)
            return 26;
         if ((active0 & 0xa200L) != 0L)
         {
            jjmatchedKind = 17;
            jjmatchedPos = 5;
            return 26;
         }
         return -1;
      case 6:
         if ((active0 & 0x8000L) != 0L)
            return 26;
         if ((active0 & 0x2200L) != 0L)
         {
            jjmatchedKind = 17;
            jjmatchedPos = 6;
            return 26;
         }
         return -1;
      default :
         return -1;
   }
}
private static final int jjStartNfa_0(int pos, long active0)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
static private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
static private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 44:
         return jjStopAtPos(0, 23);
      case 97:
         return jjMoveStringLiteralDfa1_0(0x1000L);
      case 103:
         return jjMoveStringLiteralDfa1_0(0x80L);
      case 105:
         return jjMoveStringLiteralDfa1_0(0x100L);
      case 109:
         return jjMoveStringLiteralDfa1_0(0x8000L);
      case 111:
         return jjMoveStringLiteralDfa1_0(0x200L);
      case 112:
         return jjMoveStringLiteralDfa1_0(0x400L);
      case 114:
         return jjMoveStringLiteralDfa1_0(0x2000L);
      case 115:
         return jjMoveStringLiteralDfa1_0(0x860L);
      case 123:
         return jjStopAtPos(0, 21);
      case 125:
         return jjStopAtPos(0, 22);
      default :
         return jjMoveNfa_0(0, 0);
   }
}
static private int jjMoveStringLiteralDfa1_0(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 98:
         return jjMoveStringLiteralDfa2_0(active0, 0x200L);
      case 99:
         return jjMoveStringLiteralDfa2_0(active0, 0x800L);
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0xa000L);
      case 105:
         return jjMoveStringLiteralDfa2_0(active0, 0x20L);
      case 108:
         return jjMoveStringLiteralDfa2_0(active0, 0x400L);
      case 111:
         return jjMoveStringLiteralDfa2_0(active0, 0x80L);
      case 116:
         if ((active0 & 0x1000L) != 0L)
            return jjStartNfaWithStates_0(1, 12, 26);
         return jjMoveStringLiteralDfa2_0(active0, 0x140L);
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
static private int jjMoveStringLiteralDfa2_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa3_0(active0, 0xc0L);
      case 101:
         return jjMoveStringLiteralDfa3_0(active0, 0x100L);
      case 113:
         return jjMoveStringLiteralDfa3_0(active0, 0x2000L);
      case 114:
         return jjMoveStringLiteralDfa3_0(active0, 0x800L);
      case 115:
         return jjMoveStringLiteralDfa3_0(active0, 0x8200L);
      case 117:
         return jjMoveStringLiteralDfa3_0(active0, 0x400L);
      case 122:
         return jjMoveStringLiteralDfa3_0(active0, 0x20L);
      default :
         break;
   }
   return jjStartNfa_0(1, active0);
}
static private int jjMoveStringLiteralDfa3_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 101:
         if ((active0 & 0x20L) != 0L)
            return jjStartNfaWithStates_0(3, 5, 26);
         break;
      case 103:
         return jjMoveStringLiteralDfa4_0(active0, 0x400L);
      case 105:
         return jjMoveStringLiteralDfa4_0(active0, 0x800L);
      case 108:
         if ((active0 & 0x80L) != 0L)
            return jjStartNfaWithStates_0(3, 7, 26);
         break;
      case 109:
         if ((active0 & 0x100L) != 0L)
            return jjStartNfaWithStates_0(3, 8, 26);
         break;
      case 114:
         return jjMoveStringLiteralDfa4_0(active0, 0x40L);
      case 115:
         return jjMoveStringLiteralDfa4_0(active0, 0x8000L);
      case 116:
         return jjMoveStringLiteralDfa4_0(active0, 0x200L);
      case 117:
         return jjMoveStringLiteralDfa4_0(active0, 0x2000L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0);
}
static private int jjMoveStringLiteralDfa4_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa5_0(active0, 0x8200L);
      case 105:
         return jjMoveStringLiteralDfa5_0(active0, 0x2400L);
      case 112:
         return jjMoveStringLiteralDfa5_0(active0, 0x800L);
      case 116:
         if ((active0 & 0x40L) != 0L)
            return jjStartNfaWithStates_0(4, 6, 26);
         break;
      default :
         break;
   }
   return jjStartNfa_0(3, active0);
}
static private int jjMoveStringLiteralDfa5_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0);
      return 5;
   }
   switch(curChar)
   {
      case 99:
         return jjMoveStringLiteralDfa6_0(active0, 0x200L);
      case 103:
         return jjMoveStringLiteralDfa6_0(active0, 0x8000L);
      case 110:
         if ((active0 & 0x400L) != 0L)
            return jjStartNfaWithStates_0(5, 10, 26);
         break;
      case 114:
         return jjMoveStringLiteralDfa6_0(active0, 0x2000L);
      case 116:
         if ((active0 & 0x800L) != 0L)
            return jjStartNfaWithStates_0(5, 11, 26);
         break;
      default :
         break;
   }
   return jjStartNfa_0(4, active0);
}
static private int jjMoveStringLiteralDfa6_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(4, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0);
      return 6;
   }
   switch(curChar)
   {
      case 101:
         if ((active0 & 0x8000L) != 0L)
            return jjStartNfaWithStates_0(6, 15, 26);
         return jjMoveStringLiteralDfa7_0(active0, 0x2000L);
      case 108:
         return jjMoveStringLiteralDfa7_0(active0, 0x200L);
      default :
         break;
   }
   return jjStartNfa_0(5, active0);
}
static private int jjMoveStringLiteralDfa7_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(5, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(6, active0);
      return 7;
   }
   switch(curChar)
   {
      case 101:
         if ((active0 & 0x200L) != 0L)
            return jjStartNfaWithStates_0(7, 9, 26);
         break;
      case 115:
         if ((active0 & 0x2000L) != 0L)
            return jjStartNfaWithStates_0(7, 13, 26);
         break;
      default :
         break;
   }
   return jjStartNfa_0(6, active0);
}
static private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
static final long[] jjbitVec0 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
static private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 26;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 26:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 19)
                        kind = 19;
                     jjCheckNAdd(25);
                  }
                  else if (curChar == 46)
                     jjstateSet[jjnewStateCnt++] = 23;
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 17)
                        kind = 17;
                     jjCheckNAddTwoStates(21, 22);
                  }
                  break;
               case 0:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 20)
                        kind = 20;
                     jjCheckNAdd(19);
                  }
                  else if (curChar == 45)
                     jjCheckNAdd(19);
                  else if (curChar == 34)
                     jjCheckNAddTwoStates(16, 17);
                  else if (curChar == 33)
                     jjstateSet[jjnewStateCnt++] = 11;
                  else if (curChar == 40)
                     jjCheckNAddStates(0, 2);
                  break;
               case 1:
                  if ((0x100002600L & l) != 0L)
                     jjCheckNAddStates(0, 2);
                  break;
               case 2:
                  if (curChar == 45)
                     jjCheckNAdd(3);
                  break;
               case 3:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddStates(3, 6);
                  break;
               case 4:
                  if ((0x100002600L & l) != 0L)
                     jjCheckNAddTwoStates(4, 5);
                  break;
               case 5:
                  if (curChar == 44)
                     jjCheckNAddStates(7, 9);
                  break;
               case 6:
                  if ((0x100002600L & l) != 0L)
                     jjCheckNAddStates(7, 9);
                  break;
               case 7:
                  if (curChar == 45)
                     jjCheckNAdd(8);
                  break;
               case 8:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddStates(10, 13);
                  break;
               case 9:
                  if ((0x100002600L & l) != 0L)
                     jjCheckNAddTwoStates(9, 10);
                  break;
               case 10:
                  if (curChar == 41 && kind > 14)
                     kind = 14;
                  break;
               case 12:
                  jjAddStates(14, 15);
                  break;
               case 14:
                  if (curChar == 33)
                     jjstateSet[jjnewStateCnt++] = 11;
                  break;
               case 15:
                  if (curChar == 34)
                     jjCheckNAddTwoStates(16, 17);
                  break;
               case 16:
                  if ((0xfffffffbffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(16, 17);
                  break;
               case 17:
                  if (curChar == 34 && kind > 18)
                     kind = 18;
                  break;
               case 18:
                  if (curChar == 45)
                     jjCheckNAdd(19);
                  break;
               case 19:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 20)
                     kind = 20;
                  jjCheckNAdd(19);
                  break;
               case 21:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 17)
                     kind = 17;
                  jjCheckNAddTwoStates(21, 22);
                  break;
               case 22:
                  if (curChar == 46)
                     jjstateSet[jjnewStateCnt++] = 23;
                  break;
               case 24:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 17)
                     kind = 17;
                  jjCheckNAddTwoStates(22, 24);
                  break;
               case 25:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 19)
                     kind = 19;
                  jjCheckNAdd(25);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 26:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 19)
                        kind = 19;
                     jjCheckNAdd(25);
                  }
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 17)
                        kind = 17;
                     jjCheckNAddTwoStates(21, 22);
                  }
                  break;
               case 0:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 17)
                     kind = 17;
                  jjCheckNAddStates(16, 18);
                  break;
               case 11:
                  if (curChar == 123)
                     jjCheckNAddTwoStates(12, 13);
                  break;
               case 12:
                  jjCheckNAddTwoStates(12, 13);
                  break;
               case 13:
                  if (curChar == 125 && kind > 16)
                     kind = 16;
                  break;
               case 16:
                  jjAddStates(19, 20);
                  break;
               case 21:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 17)
                     kind = 17;
                  jjCheckNAddTwoStates(21, 22);
                  break;
               case 23:
               case 24:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 17)
                     kind = 17;
                  jjCheckNAddTwoStates(22, 24);
                  break;
               case 25:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 19)
                     kind = 19;
                  jjCheckNAdd(25);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 12:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjAddStates(14, 15);
                  break;
               case 16:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjAddStates(19, 20);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 26 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
   1, 2, 3, 2, 3, 4, 5, 6, 7, 8, 7, 8, 9, 10, 12, 13, 
   21, 22, 25, 16, 17, 
};

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, "\163\151\172\145", "\163\164\141\162\164", 
"\147\157\141\154", "\151\164\145\155", "\157\142\163\164\141\143\154\145", 
"\160\154\165\147\151\156", "\163\143\162\151\160\164", "\141\164", "\162\145\161\165\151\162\145\163", 
null, "\155\145\163\163\141\147\145", null, null, null, null, null, "\173", "\175", 
"\54", };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
};
static final long[] jjtoToken = {
   0xffffe1L, 
};
static final long[] jjtoSkip = {
   0x1eL, 
};
static protected SimpleCharStream input_stream;
static private final int[] jjrounds = new int[26];
static private final int[] jjstateSet = new int[52];
static protected char curChar;
/** Constructor. */
public ParserTokenManager(SimpleCharStream stream){
   if (input_stream != null)
      throw new TokenMgrError("ERROR: Second call to constructor of static lexer. You must use ReInit() to initialize the static variables.", TokenMgrError.STATIC_LEXER_ERROR);
   input_stream = stream;
}

/** Constructor. */
public ParserTokenManager(SimpleCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
static public void ReInit(SimpleCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
static private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 26; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
static public void ReInit(SimpleCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
static public void SwitchTo(int lexState)
{
   if (lexState >= 1 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

static protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

static int curLexState = 0;
static int defaultLexState = 0;
static int jjnewStateCnt;
static int jjround;
static int jjmatchedPos;
static int jjmatchedKind;

/** Get the next Token. */
public static Token getNextToken() 
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   try { input_stream.backup(0);
      while (curChar <= 32 && (0x100002600L & (1L << curChar)) != 0L)
         curChar = input_stream.BeginToken();
   }
   catch (java.io.IOException e1) { continue EOFLoop; }
   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         return matchedToken;
      }
      else
      {
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try { input_stream.readChar(); input_stream.backup(1); }
   catch (java.io.IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
   }
   throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

static private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
static private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
static private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

static private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

}
