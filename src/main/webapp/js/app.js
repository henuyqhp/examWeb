function Todate(num) { //Sun Dec 10 16:16:54 CST 2017

    num = num + "";

    var date = "";

    var month = new Array();

    month["Jan"] = 1; month["Feb"] = 2; month["Mar"] = 3; month["Apr"] = 4; month["May"] = 5; month["Jan"] = 6;

    month["Jul"] = 7; month["Aug"] = 8; month["Sep"] = 9; month["Oct"] = 10; month["Nov"] = 11; month["Dec"] = 12;

    var week = new Array();

    week["Mon"] = "一"; week["Tue"] = "二"; week["Wed"] = "三"; week["Thu"] = "四"; week["Fri"] = "五"; week["Sat"] = "六"; week["Sun"] = "日";

    str = num.split(" ");

    date = str[5] + "-";

    time = str[3].split(":")

    date = date + month[str[1]] + "-" + str[2] + " " + time[0]+"."+time[1];

    return date;
}

function ToDate2(num) {
    var date = new Date(num);
    var result = date.getFullYear()+"-" + (date.getMonth()+1) +"-" + date.getDate() + " " + date.getHours() + "." + date.getMinutes();
    return result;
}