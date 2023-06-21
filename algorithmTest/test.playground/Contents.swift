import UIKit


struct Question {
    var question:String = ""
    var checkExisting:String = ""
    var correct:Bool = false
    var options:[Responses]
}

struct Responses {
    var result:Int = 0
    var correct:Bool = false
    var selected:Bool = false
}

//Test variables
var listNumber1 = [2,3,5]
var listNumber2 = [1,2,3,4,5,6,7,8,9,10,11]
var listOperations = ["+","-","x","÷"]
var numberQuestions = 15


func checkExisting(check new:String, inside questions:[Question])-> Bool {
    return questions.contains{ c in
        return c.checkExisting == new
    }
}

func buildQuestions(a:[Int], b:[Int], operations:[String], count:Int)->[Question] {
    
    var questions = [Question]()
    
    repeat {
        
        var na = a.randomElement()!
        var nb = b.randomElement()!
        var o = operations.randomElement()!
        var question = ""
        var existing = ""
        var re = 0
        switch o {
            case "+":
                re = na + nb
                question = "\(na) \(o) \(nb)= __"
                existing = "\(na)\(o)\(nb)=\(re)"
                break
            case "-":
                re = na + nb
                question = "\(re) \(o) \(na) = __"
                existing = "\(re)\(o)\(na)=\(nb)"
                break
            case "x":
                re = na * nb
                question = "\(na) \(o) \(nb) = __"
                existing = "\(na)\(o)\(nb)=\(re)"
                break
            case "÷":
                re = na * nb
                question = "\(re) \(o) \(na) = __"
                existing = "\(re)\(o)\(na)=\(nb)"
                break
            default:
                re = 0
        }
        var op = [Responses]()
        op.append(Responses(result: re,correct: true))
        for j in 1..<4 {
            if([1,2].randomElement() == 1){
                op.append(Responses(result: re+j, correct: false))
            }else{
                op.append(Responses(result: re-j, correct: false))
            }
        }
        //print("\(question) \(existing)")
        //print(checkExisting(check: existing, inside: questions))
        if(!checkExisting(check: existing, inside: questions)){
            questions.append(Question(question: question, checkExisting: existing, correct: false, options: op.shuffled()))
        }
    } while questions.count < count
    //print(questions.count)
    return questions.shuffled()
}


var temp = buildQuestions(a: listNumber1, b: listNumber2, operations: listOperations, count: numberQuestions)

for el in temp {
    print(el.question)
    for pr in el.options {
        print("\(pr.result) -> \(pr.correct)")
    }
}
