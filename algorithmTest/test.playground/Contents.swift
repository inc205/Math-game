import UIKit


struct Question {
    var num1:Int = 0
    var num2:Int? = 0
    var operation:String = "+"
    var checkExisting:String = ""
    var result:Int? = 0
    var correct:Bool = false
    var options:[Responses]
}

struct Responses {
    var result:Int=0
    var correct:Bool = false
}

//Test variables
var listNumber1 = [2,3,5]
var listNumber2 = [1,2,3,4,5,6,7,8,9]
var listOperations = ["+","-","x"]
var numberQuestions = 15


func checkExisting(check new:String, inside questions:[Question])-> Bool {
    
    return questions.contains{ c in
        return c.checkExisting == new
    }
}

func buildQuestions(a:[Int], b:[Int], operations:[String], count:Int)->[Question] {
    
    var questions = [Question]()
    
    for _ in 0..<count {
        
        var na = a.randomElement()!
        var nb = b.randomElement()!
        var o = operations.randomElement()!
        var re = 0
        switch o {
            case "+":
                re = na + nb
                break
            case "-":
                re = na - nb
                break
            case "x":
                re = na * nb
                break
        default:
            re = 0
        }
        var op = [Responses]()
        op.append(Responses(result: re,correct: true))
        for j in 0..<3 {
            op.append(Responses(result: re+j, correct: false))
        }
        print("\(na)\(o)\(nb)=\(re)")
        print(checkExisting(check: "\(na)\(o)\(nb)=\(re)", inside: questions))
        questions.append(Question(num1: na, num2: nb, operation: o, checkExisting: "\(na)\(o)\(nb)=\(re)", correct: false, options: op.shuffled()))
    }
    
    return questions.shuffled()
}


print(buildQuestions(a: listNumber1, b: listNumber2, operations: listOperations, count: numberQuestions))
