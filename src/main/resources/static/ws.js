import SockJS from 'sockjs-client'
import {Stomp} from "@stomp/stompjs";

let stompClient = null;
const handlers = [];

export function connect() {
    let socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected);
}

export function onConnected(to){
    console.log("connected");
    stompClient.subscribe(
        "/topic/to/".concat(to),
        message => {
            handlers.forEach(handler => handler(JSON.parse(message.body)))
        }
    );
}

export function sendMessage(message){
    stompClient.send("/app/send", {}, JSON.stringify(message));
}

// export function disconnect() {
//     if (stompClient) {
//         stompClient.disconnect();
//     }
//     stompClient = null;
// }

export function addHandler(handler) {
    handlers.push(handler)
}

export function getIndex(list, id) {
    for (let i = 0; i < list.length; i++ ) {
        if (list[i].id === id) {
            return i
        }
    }
    return -1
}