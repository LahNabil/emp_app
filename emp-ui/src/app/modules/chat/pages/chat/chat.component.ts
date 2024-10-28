import { Component } from '@angular/core';
import { ChatService } from '../../services/chat.service';
import { QuestionResponse } from '../../../../models/QuestionResponse';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent {
  question: string = '';
  response: string = ''; // This will store the actual response text
  isLoading: boolean = false;

  constructor(private chatService: ChatService) {}

  askQuestion() {
    if (this.question.trim()) {
      this.isLoading = true;
      this.chatService.chat(this.question).subscribe({
        next: (res: QuestionResponse) => {
          this.response = res.response ?? 'No response received.'; // Handles undefined response
          this.isLoading = false;
        },
        error: (err) => {
          console.error('Error:', err);
          this.response = 'An error occurred. Please try again.';
          this.isLoading = false;
        }
      });
    }
  }
}
